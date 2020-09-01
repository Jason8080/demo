package com.gm.demo.nacos.server.api.fallback;

import com.gm.demo.nacos.server.api.HelloApi;
import org.springframework.stereotype.Component;

@Component
public class ApiFallback implements HelloApi {
    @Override
    public String hello() {
        return "不好意思, 熔断咯..";
    }

    @Override
    public Object helloPage() {
        return "不好意思, 熔断咯..";
    }
}
