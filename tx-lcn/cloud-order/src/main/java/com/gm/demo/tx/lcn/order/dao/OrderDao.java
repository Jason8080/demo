package com.gm.demo.tx.lcn.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Order dao.
 *
 * @author Jason
 */
public interface OrderDao {
    /**
     * Insert.
     *
     * @param name the name
     */
    @Insert("insert into `order` (name) values(#{name})")
    void insert(
            @Param("name") String name
    );
}
