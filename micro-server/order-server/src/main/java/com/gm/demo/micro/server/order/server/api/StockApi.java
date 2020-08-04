package com.gm.demo.micro.server.order.server.api;

import com.gm.demo.micro.server.order.server.api.entity.StockParams;
import com.gm.demo.micro.server.order.server.api.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

/**
 * The interface Stock api.
 *
 * @author Jason
 */
@FeignClient(value = "STOCK-SERVER", fallback = ApiFallback.class)
public interface StockApi {

    /**
     * Sub.
     *
     * @param sp the sp
     */
    @RequestMapping(
            value = "stock/sub",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA
    )
    void sub(StockParams sp);
}
