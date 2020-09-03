package com.gm.demo.nacos.server.common.global;

import com.gm.demo.nacos.server.common.ex.SkillException;
import com.gm.demo.nacos.server.common.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * 通用鉴权控制器
 *
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@Validated
public class AuthController {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Autowired
    RedisClient<String, String> redisClient;

    public static final String TOKEN_PREFIX = "POS:AUTH:TOKEN_";

    @ModelAttribute
    public void preHandler(
            @RequestHeader(value = "token", required = false) String token
    ){
        if(StringUtils.isEmpty(token)){
            throw new SkillException("请先登录", 502);
        }
        ServletRequestAttributes sra = (ServletRequestAttributes)
                // RequestContextHolder:
                //  配合@ModelAttribute使用, 避免Service另起线程Request空异常风险
                RequestContextHolder.getRequestAttributes();
        request = sra.getRequest();
        response = sra.getResponse();
        String old = redisClient.get(TOKEN_PREFIX.concat(token));
        if(StringUtils.isEmpty(old)){
            throw new SkillException("请先登录", 502);
        }
    }

}
