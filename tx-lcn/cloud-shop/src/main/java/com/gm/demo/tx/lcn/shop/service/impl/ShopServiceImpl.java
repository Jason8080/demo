package com.gm.demo.tx.lcn.shop.service.impl;

import com.gm.demo.tx.lcn.api.service.AccountService;
import com.gm.demo.tx.lcn.api.service.OrderService;
import com.gm.demo.tx.lcn.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;

    @Override
    public void buy(Long id, String name, BigDecimal amount) {
        // 生成订单
        orderService.buy(id, name);
        // 账户扣钱
        accountService.pay(id, amount);
    }

}
