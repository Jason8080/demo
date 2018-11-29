package com.gm.demo.tx.lcn.shop.service;

import java.math.BigDecimal;

/**
 * The interface Shop service.
 *
 * @author Jason
 */
public interface ShopService {
    /**
     * Buy.
     *
     * @param id     the id
     * @param name   the name
     * @param amount the amount
     */
    void buy(Long id, String name, BigDecimal amount);
}
