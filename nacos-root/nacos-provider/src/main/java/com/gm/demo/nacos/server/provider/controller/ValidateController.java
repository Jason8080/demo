package com.gm.demo.nacos.server.provider.controller;

import com.gm.demo.nacos.server.common.ctrl.AuthorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试认证控制器
 *
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@RestController
public class ValidateController extends AuthorController {

    @RequestMapping("hello2")
    public String hello2(int a) {
        System.out.println(a);
        return "OK";
    }
}
