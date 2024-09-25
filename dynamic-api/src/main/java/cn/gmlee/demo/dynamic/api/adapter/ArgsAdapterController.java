package cn.gmlee.demo.dynamic.api.adapter;

import cn.gmlee.tools.base.anno.ApiPrint;
import cn.gmlee.tools.base.mod.R;
import cn.gmlee.tools.base.util.WebUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ArgsAdapterController {

    @ApiPrint("动态接口 · 业务中心")
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
