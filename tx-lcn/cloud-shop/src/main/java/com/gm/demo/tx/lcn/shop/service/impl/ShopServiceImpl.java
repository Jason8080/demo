package com.gm.demo.tx.lcn.shop.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gm.demo.tx.lcn.api.service.AccountService;
import com.gm.demo.tx.lcn.api.service.OrderService;
import com.gm.demo.tx.lcn.shop.dao.ShopDao;
import com.gm.demo.tx.lcn.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    ShopDao shopDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TxTransaction(isStart = true)
    public void buy(Long id, String name, BigDecimal amount) {
        // 销售记录
        shopDao.insert(name, amount);
        // 生成订单
        orderService.buy(id, name);
        // 账户扣钱
        accountService.pay(id, amount);
    }

}
