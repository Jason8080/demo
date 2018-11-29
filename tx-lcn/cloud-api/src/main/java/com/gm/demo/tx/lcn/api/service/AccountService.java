package com.gm.demo.tx.lcn.api.service;

import com.gm.demo.tx.lcn.api.service.fallback.DefaultFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * The interface Account service.
 *
 * @author Jason
 */
@FeignClient(
        name = "account"
//        ,value = "192.168.1.223"
//        ,path = "/account"
//        ,configuration = FeignConfiguration.class
        ,fallback = DefaultFallback.class
)
public interface AccountService {
    /**
     * Pay.
     *
     * @param id     用户号
     * @param amount 总金额
     */
    @RequestMapping(value = "account/pay", method = RequestMethod.GET)
    void pay(@RequestParam("id") Long id, @RequestParam("amount") BigDecimal amount);
}
