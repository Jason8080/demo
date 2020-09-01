package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gm.demo.nacos.server.common.util.RedisLock;
import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.provider.mapper.entity.User;
import com.gm.demo.nacos.server.provider.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.gm.demo.nacos.server")
public class NacosProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApp.class, args);
    }

    private Logger log = LoggerFactory.getLogger(NacosProviderApp.class);

    @Autowired
    UserService userService;
    @Autowired
    RedisLock redisLock;

    @GetMapping("helloPage")
    @SentinelResource("helloPage")
    public JsonResult<IPage<User>> helloPage() throws InterruptedException {
        if(redisLock.lock("HELLO_PAGE")) {
            Thread.sleep(200);
            log.info("计数器+1");
        }else {
            log.info("没拿到哦...");
        }
        return JsonResult.OK.newly(userService.selectPage());
    }

    @GetMapping("hello")
    @SentinelResource("hello")
    public JsonResult<IPage<User>> hello(){
        log.error("计数器-1");
        return JsonResult.OK.newly(userService.selectList());
    }
}
