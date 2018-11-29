package com.gm.demo.tx.lcn.shop.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * The interface Shop dao.
 *
 * @author Jason
 */
public interface ShopDao {

    /**
     * Insert.
     *
     * @param name   the name
     * @param amount the amount
     */
    @Insert("insert into `shop` (name,amount) values(#{name},#{amount})")
    void insert(
                @Param("name") String name,
                @Param("amount") BigDecimal amount
    );
}
