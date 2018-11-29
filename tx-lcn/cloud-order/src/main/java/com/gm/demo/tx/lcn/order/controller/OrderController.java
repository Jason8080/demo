package com.gm.demo.tx.lcn.order.controller;

import com.gm.utils.base.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public void buy(Long id, String name) {
        Logger.info(id+": "+name);
    }
}
