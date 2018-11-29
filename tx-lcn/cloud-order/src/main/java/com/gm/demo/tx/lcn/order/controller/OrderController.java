package com.gm.demo.tx.lcn.order.controller;

import com.gm.demo.tx.lcn.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public void buy(Long id, String name) {
        orderService.buy(id,name);
    }
}
