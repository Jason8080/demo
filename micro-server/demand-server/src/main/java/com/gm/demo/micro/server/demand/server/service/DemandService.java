package com.gm.demo.micro.server.demand.server.service;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;

/**
 * The interface Demand service.
 *
 * @author Jason
 */
public interface DemandService {
    /**
     * Grab order string.
     *
     * @param gop the gop
     * @return the string
     */
    String grabOrder(GrabOrderParams gop);
}
