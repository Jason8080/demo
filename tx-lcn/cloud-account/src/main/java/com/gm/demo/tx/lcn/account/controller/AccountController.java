package com.gm.demo.tx.lcn.account.controller;

import com.gm.demo.tx.lcn.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Jason
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public void buy(Long id, BigDecimal amount) {
        accountService.pay(id, amount);
    }
}
