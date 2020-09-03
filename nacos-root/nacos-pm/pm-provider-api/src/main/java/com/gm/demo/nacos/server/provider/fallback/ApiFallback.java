package com.gm.demo.nacos.server.provider.fallback;

import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.provider.HelloApi;
import org.springframework.stereotype.Component;

/**
 * @author Timi
 */
@Component
public class ApiFallback implements HelloApi {
    @Override
    public JsonResult hello() {
        return JsonResult.FAIL.newly("不好意思, 熔断咯..");
    }

    @Override
    public JsonResult helloPage() {
        return JsonResult.FAIL.newly("不好意思, 熔断咯..");
    }
}
