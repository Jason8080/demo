package com.gm.demo.nacos.server.common.fallback;


import com.gm.demo.nacos.server.common.mod.JsonResult;

/**
 * @author DELL
 */
public class ApiFallback {
    public JsonResult fallback() {
        return JsonResult.FAIL.newly("不好意思, 熔断咯..");
    }
}
