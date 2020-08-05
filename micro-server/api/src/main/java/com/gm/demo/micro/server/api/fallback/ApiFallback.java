package com.gm.demo.micro.server.api.fallback;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.api.entity.StockParams;
import com.gm.demo.micro.server.api.order.GrabOrderApi;
import com.gm.demo.micro.server.api.stock.StockApi;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ApiFallback implements GrabOrderApi, StockApi {
    @Override
    public String grabOrder(GrabOrderParams gop) {
        return "error";
    }

    @Override
    public void sub(StockParams sp) {
        System.out.println("熔断器: ==================");
    }
}
