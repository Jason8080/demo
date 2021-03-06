package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gm.demo.nacos.server.common.mod.JsonResult;
import com.gm.demo.nacos.server.common.util.RedisUtil;
import com.gm.demo.nacos.server.provider.dao.entity.User;
import com.gm.demo.nacos.server.provider.service.UserService;
import io.swagger.annotations.ApiOperation;
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
    RedisUtil redisUtil;

    @GetMapping("helloPage")
    @ApiOperation("访问分页数据")
    @SentinelResource("helloPage")
    public JsonResult<IPage<User>> helloPage() throws InterruptedException {
        if(redisUtil.rl().lock("HELLO_PAGE")) {
            Thread.sleep(200);
            log.info("计数器+1");
        }else {
            log.info("没拿到哦...");
        }
        return JsonResult.OK.newly(userService.selectPage());
    }

    @GetMapping("hello")
    @ApiOperation("访问数据")
    @SentinelResource("hello")
    public JsonResult<IPage<User>> hello(){
        log.error("计数器-1");
        return JsonResult.OK.newly(userService.selectList());
    }
}
