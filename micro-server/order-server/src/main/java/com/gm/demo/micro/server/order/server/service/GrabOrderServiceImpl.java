package com.gm.demo.micro.server.order.server.service;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.api.entity.StockParams;
import com.gm.demo.micro.server.api.order.GrabOrderApi;
import com.gm.demo.micro.server.api.stock.StockApi;
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
        return UUID.randomUUID().toString();
    }
}
