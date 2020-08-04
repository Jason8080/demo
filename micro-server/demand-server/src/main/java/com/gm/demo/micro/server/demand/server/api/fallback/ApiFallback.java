package com.gm.demo.micro.server.demand.server.api.fallback;

import com.gm.demo.micro.server.demand.server.api.GrabOrderApi;
import com.gm.demo.micro.server.demand.server.api.entity.GrabOrderParams;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class ApiFallback implements GrabOrderApi {
    @Override
    public String grabOrder(GrabOrderParams gop) {
        return "error";
    }
}
