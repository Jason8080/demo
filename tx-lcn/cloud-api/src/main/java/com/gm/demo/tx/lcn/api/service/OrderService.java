package com.gm.demo.tx.lcn.api.service;

import com.gm.demo.tx.lcn.api.service.fallback.DefaultFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Order service.
 *
 * @author Jason
 */
@FeignClient(
        name = "order"
//        ,value = "192.168.1.223"
//        ,path = "/order"
//        ,configuration = FeignConfiguration.class
        ,fallback = DefaultFallback.class
)
public interface OrderService {
    /**
     * Buy.
     *
     * @param id   用户号
     * @param name 商品名
     */
    @RequestMapping(value = "order/buy", method = RequestMethod.GET)
    void buy(@RequestParam("id") Long id, @RequestParam("name") String name);
}
