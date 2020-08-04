package com.gm.demo.micro.server.api.stock;

import com.gm.demo.micro.server.api.entity.StockParams;
import com.gm.demo.micro.server.api.fallback.ApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "stock/sub", method = RequestMethod.POST)
    void sub(StockParams sp);
}
