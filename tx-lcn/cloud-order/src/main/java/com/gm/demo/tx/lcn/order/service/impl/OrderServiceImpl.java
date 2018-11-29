package com.gm.demo.tx.lcn.order.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gm.demo.tx.lcn.api.service.OrderService;
import com.gm.utils.base.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    @TxTransaction
    public void buy(Long id, String name) {
        Logger.info(id+": "+name);
    }
}
