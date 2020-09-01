package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gm.demo.nacos.server.common.config.redis.RedisLock;
import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.provider.mapper.entity.User;
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
    @Autowired
    RedisLock redisLock;

    @GetMapping("helloPage")
    @SentinelResource("helloPage")
    public JsonResult<IPage<User>> helloPage() throws InterruptedException {
        if(redisLock.lock("HELLO_PAGE")) {
            Thread.sleep(200);
            System.out.println("计数器+1");
        }else {
            System.out.println("没拿到哦...");
        }
        return JsonResult.OK.newly(userService.selectPage());
    }

    @GetMapping("hello")
    @SentinelResource("hello")
    public JsonResult<IPage<User>> hello(){
        System.out.println("计数器+1");
        if(true) throw new RuntimeException("666");
        return JsonResult.OK.newly(userService.selectList());
    }
}
