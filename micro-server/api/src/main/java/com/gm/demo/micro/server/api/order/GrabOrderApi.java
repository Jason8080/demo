package com.gm.demo.micro.server.api.order;

import com.gm.demo.micro.server.api.entity.GrabOrderParams;
import com.gm.demo.micro.server.api.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The interface Grab order api.
 *
 * @author Jason
 */
@FeignClient(value = "ORDER-SERVER", fallback = ApiFallback.class)
public interface GrabOrderApi {
    /**
     * Grab order string.
     *
     * @param gop the gop
     * @return the string
     */
    @RequestMapping(value = "order/grabOrder", method = RequestMethod.POST )
    String grabOrder(GrabOrderParams gop) ;
}
