package com.gm.demo.tx.lcn.account.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gm.demo.tx.lcn.api.service.AccountService;
import com.gm.utils.base.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The type Account service.
 *
 * @author Jason
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    @TxTransaction
    public void pay(Long id, BigDecimal amount) {
        Logger.info(id+": "+amount.doubleValue());
    }
}
