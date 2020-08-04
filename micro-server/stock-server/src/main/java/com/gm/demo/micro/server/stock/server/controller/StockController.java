package com.gm.demo.micro.server.stock.server.controller;

import com.gm.demo.micro.server.stock.server.api.entity.StockParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class StockController {

    @RequestMapping(value = "stock/sub", method = RequestMethod.POST)
    public void sub(StockParams sp) {
        System.out.println("库存服务: 扣减库存成功!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
