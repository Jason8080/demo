package cn.gmlee.demo.dynamic.api.adapter;

import cn.gmlee.tools.base.mod.R;
import cn.gmlee.tools.base.util.WebUtil;
import cn.gmlee.tools.spring.util.MvcUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArgsAdapterController {

    @ResponseBody
    public R handle(@RequestBody Map<String, Object> bodyMap) {
        HttpServletRequest request = WebUtil.getRequest();
        String relativePath = WebUtil.getRelativePath(request);
        System.out.println(relativePath);
        Map<String, String[]> queryMap = request.getParameterMap();
        System.out.println(queryMap);
        System.out.println(bodyMap);
        Map<String, String> headerMap = WebUtil.getCurrentHeaderMap();
        System.out.println(headerMap);
        Map<String, String> cookieMap = WebUtil.getCookieMap(request);
        System.out.println(cookieMap);
        return R.OK;
    }
}
