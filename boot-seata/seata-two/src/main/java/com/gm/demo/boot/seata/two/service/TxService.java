package com.gm.demo.boot.seata.two.service;

import com.gm.demo.boot.seata.main.api.entity.Tx;
import com.gm.demo.boot.seata.two.dao.mapper.TxMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jas°
 * @date 2021/2/24 (周三)
 */
@Service
public class TxService {

    @Resource
    TxMapper txMapper;

    public void come(Tx tx) {
        txMapper.insert(tx);
    }
}
