package com.gm.demo.nacos.server.common.ctrl;

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
public class AuthorController {

    private HttpServletRequest request;
    private HttpServletResponse response;

    // 该用法必须配合RequestContextHolder使用, 否则在多url场景下有并发风险
    @ModelAttribute
    public void preHandler(
            @NotNull(message = "请先登陆")
            @RequestHeader(value = "token", required = false) String token
    ){
        ServletRequestAttributes sra = (ServletRequestAttributes)
                // RequestContextHolder:
                //  配合@ModelAttribute使用, 避免Service另起线程Request空异常风险
                RequestContextHolder.getRequestAttributes();
        request = sra.getRequest();
        response = sra.getResponse();
    }

}
