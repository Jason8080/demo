package com.gm.demo.nacos.server.provider;

import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.provider.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

/**
 * The interface Hello api.
 *
 * @author Timi
 */
@FeignClient(value = "nacos-provider", fallback = ApiFallback.class)
public interface HelloApi {
    /**
     * Grab order string.
     *
     * @return the string
     */
    @RequestMapping(
            value = "hello",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON
    )
    JsonResult hello() ;

    /**
     * Hello page json result.
     *
     * @return the json result
     */
    @RequestMapping(
            value = "helloPage",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON
    )
    JsonResult helloPage() ;
}

