package com.gm.demo.nacos.server.api;

import com.gm.demo.nacos.server.api.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

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
    Object hello() ;
    @RequestMapping(
            value = "helloPage",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON
    )
    Object helloPage() ;
}

