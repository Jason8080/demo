package com.gm.demo.micro.server.order.server.service;


import com.gm.demo.micro.server.order.server.api.entity.GrabOrderParams;

/**
 * The interface Grab order service.
 *
 * @author Jason
 */
public interface GrabOrderService {
    /**
     * Grab order string.
     *
     * @param gop the gop
     * @return the string
     */
    String grabOrder(GrabOrderParams gop);
}
