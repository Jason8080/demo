package com.gm.demo.nacos.server.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控服务
 *
 * @author Timi.lee
 * @date 2020/8/24 (周一)
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorApp {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApp.class, args);
    }


}
