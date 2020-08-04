package com.gm.demo.micro.server.order.server.service;

import com.gm.demo.micro.server.order.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.order.server.api.entity.StockParams;
import com.gm.demo.micro.server.order.server.api.StockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Jason
 */
@Service
public class GrabOrderServiceImpl implements GrabOrderService {

    @Autowired
    StockApi stockApi;

    @Override
    public String grabOrder(GrabOrderParams gop) {
        stockApi.sub(new StockParams());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return UUID.randomUUID().toString();
    }
}
