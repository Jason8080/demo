package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gm.demo.nacos.server.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.gm.demo.nacos.server")
@PropertySource({
        "classpath:application.properties",
        "classpath:common.properties"
})
public class NacosProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApp.class, args);
    }

    @Autowired
    UserService userService;

    @GetMapping("hello")
    @SentinelResource("hello")
    public Object hello(){
        System.out.println("计数器+1");
        return userService.selectList();
    }
}
