package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.common.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.gm.demo.nacos.server.provider"})
@ComponentScan("com.gm.demo.nacos.server")
public class NacosConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApp.class, args);
    }

    @Autowired
    private HelloApi helloApi;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("testPage")
    @SentinelResource("testPage")
    @ApiOperation("测试分页数据")
    public JsonResult testPage() {
        return JsonUtil.o2o(helloApi.helloPage(), JsonResult.class);
    }

    @GetMapping("test")
    @SentinelResource("test")
    @ApiOperation("测试数据")
    public JsonResult test() {
        return JsonUtil.o2o(helloApi.hello(), JsonResult.class);
    }
}