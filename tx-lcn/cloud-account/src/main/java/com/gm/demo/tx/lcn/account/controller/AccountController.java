package com.gm.demo.tx.lcn.account.controller;

import com.gm.utils.base.Logger;
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

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public void buy(Long id, BigDecimal amount) {
        Logger.info(id+": "+amount.doubleValue());
    }
}
