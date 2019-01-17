package com.gm.demo.fabric.manual.controller;

import com.gm.demo.fabric.manual.service.FabricServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
@Api(tags = "演示控制器")
public class DemoController {

    @Autowired
    FabricServiceImpl fabricService;

    @GetMapping("start")
    public Object start() {
        fabricService.handler();
        return 200;
    }
}
