package com.gm.demo.tx.lcn.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * The interface Account dao.
 *
 * @author Jason
 */
public interface AccountDao {
    /**
     * Insert.
     *
     * @param amount the amount
     */
    @Insert("insert into `account` (amount) values(#{amount})")
    void insert(
            @Param("amount") BigDecimal amount
    );
}
