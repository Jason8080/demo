package com.gm.demo.tx.lcn.api.service.fallback;

import com.gm.demo.tx.lcn.api.service.AccountService;
import com.gm.demo.tx.lcn.api.service.OrderService;
import com.gm.utils.base.Logger;

import java.math.BigDecimal;

/**
 * @author Jason
 */
public class DefaultFallback implements OrderService,AccountService {
    @Override
    public void buy(Long id, String name) {
        Logger.warn("熔断了" + id + ": " + name);
    }
    @Override
    public void pay(Long id, BigDecimal amount) {
        Logger.warn("熔断了"+id+": "+amount.doubleValue());
    }
}