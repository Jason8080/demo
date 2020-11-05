package com.gm.common.dynamic.dao.mapper;

import com.gm.common.dynamic.dao.entity.Tx;

/**
 * @author Timi.lee
 * @date 2020/11/5 (周四)
 */
public interface TxMapper {
    /**
     * 保存
     *
     * @param tx
     */
//    @Insert("insert into tx (id, str) values(null,#{str})")
    void insert(Tx tx);
}
