package com.gm.common.dynamic.service;

import cn.gmlee.tools.mysql.anno.ReadOnly;
import com.gm.common.dynamic.dao.entity.Tx;
import com.gm.common.dynamic.dao.mapper.TxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Timi.lee
 * @date 2020/11/5 (周四)
 */
@Service
public class TxService {
    @Autowired
    TxMapper txMapper;

    @ReadOnly
    public void save(Tx tx) {
        txMapper.insert(tx);
    }
}
