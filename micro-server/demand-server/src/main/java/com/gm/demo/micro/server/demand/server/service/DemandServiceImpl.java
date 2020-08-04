package com.gm.demo.micro.server.demand.server.service;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.demand.server.api.GrabOrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    GrabOrderApi grabOrderApi;

    /**
     * 抢单请求
     * @param gop the gop
     * @return
     */
    @Override
    public String grabOrder(GrabOrderParams gop) {
        return grabOrderApi.grabOrder(gop);
    }
}
