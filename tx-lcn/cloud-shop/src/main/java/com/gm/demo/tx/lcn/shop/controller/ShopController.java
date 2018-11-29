package com.gm.demo.tx.lcn.shop.controller;

import com.gm.demo.tx.lcn.shop.service.ShopService;
import com.gm.model.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * The type Shop controller.
 *
 * @author Jason
 */
@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    /**
     * Buy.
     *
     * @param id     the id
     * @param name   the name
     * @param amount the amount
     */
    @GetMapping("buy")
    public JsonResult buy(Long id, String name, BigDecimal amount) {
        shopService.buy(id, name, amount);
        return JsonResult.SUCCESS_;
    }

}
