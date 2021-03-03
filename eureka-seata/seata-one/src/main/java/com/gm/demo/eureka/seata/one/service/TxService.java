package com.gm.demo.eureka.seata.one.service;

import com.gm.demo.eureka.seata.main.api.entity.Tx;
import com.gm.demo.eureka.seata.one.dao.mapper.TxMapper;
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
