package com.gm.demo.micro.server.order.server.controller;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.order.server.service.GrabOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class GrabOrderController {

    @Autowired
    GrabOrderService grabOrderService;

    @RequestMapping(value = "order/grabOrder", method = RequestMethod.POST)
    public Object grabOrder(GrabOrderParams gop) {
        System.out.println("订单服务: 接受到抢单请求!");
        return grabOrderService.grabOrder(gop);
    }
}
