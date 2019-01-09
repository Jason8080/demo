package com.gm.demo.fabric.manual.controller;

import com.gm.demo.fabric.manual.service.FabricServiceImpl;
import com.gm.model.response.JsonResult;
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
    public JsonResult start(String name) {
        fabricService.handler(name);
        return JsonResult.SUCCESS_;
    }
}
