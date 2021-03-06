package com.gm.demo.boot.seata.two.api;

import cn.gmlee.tools.base.mod.JsonResult;
import cn.gmlee.tools.cloud.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface One app.
 *
 * @author Jas °
 * @date 2021 /2/24 (周三)
 */
@FeignClient(value = "seata-two", fallback = ApiFallback.class)
public interface TwoApi {

    /**
     * Come json result.
     *
     * @param str the str
     * @return the json result
     */
    @RequestMapping(value = "two", method = RequestMethod.POST)
    JsonResult come(@RequestParam("str") String str) ;
}
