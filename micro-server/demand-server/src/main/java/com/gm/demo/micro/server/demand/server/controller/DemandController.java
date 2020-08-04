package com.gm.demo.micro.server.demand.server.controller;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.demand.server.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
@RequestMapping("demand")
public class DemandController {
    @Autowired
    DemandService demandService;


    @RequestMapping("grabOrder")
    public Object grabOrder(GrabOrderParams gop) {
        System.out.println("需求服务: 接收到抢单请求!");
        return demandService.grabOrder(gop);
    }
}
