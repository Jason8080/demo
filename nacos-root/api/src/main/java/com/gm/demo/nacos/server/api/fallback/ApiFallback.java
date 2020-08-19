package com.gm.demo.nacos.server.api.fallback;

import com.gm.demo.nacos.server.api.HelloApi;
import org.springframework.stereotype.Component;

@Component
public class ApiFallback implements HelloApi {
    public String hello() {
        return "不好意思, 熔断咯..";
    }
}
