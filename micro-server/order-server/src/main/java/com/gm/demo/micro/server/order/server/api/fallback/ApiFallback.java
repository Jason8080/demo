package com.gm.demo.micro.server.order.server.api.fallback;

import com.gm.demo.micro.server.order.server.api.StockApi;
import com.gm.demo.micro.server.order.server.api.entity.StockParams;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ApiFallback implements StockApi {
    @Override
    public void sub(StockParams sp) {
        System.out.println("熔断器: ============================");
    }
}
