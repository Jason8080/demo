package com.gm.demo.tx.lcn.order.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gm.demo.tx.lcn.api.service.OrderService;
import com.gm.demo.tx.lcn.order.dao.OrderDao;
import com.gm.utils.base.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jason
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    @TxTransaction
    @Transactional(rollbackFor = Exception.class)
    public void buy(Long id, String name) {
        orderDao.insert(name);
        Logger.info(id+": "+name);
    }
}
