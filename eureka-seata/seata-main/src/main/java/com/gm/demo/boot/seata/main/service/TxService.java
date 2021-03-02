package com.gm.demo.boot.seata.main.service;

import cn.gmlee.tools.base.util.AssertUtil;
import com.gm.demo.boot.seata.main.api.entity.Tx;
import com.gm.demo.boot.seata.main.api.enums.MicroConstant;
import com.gm.demo.boot.seata.main.dao.mapper.TxMapper;
import com.gm.demo.boot.seata.one.api.OneApi;
import com.gm.demo.boot.seata.two.api.TwoApi;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Resource
    OneApi oneApi;
    @Resource
    TwoApi twoApi;

    @GlobalTransactional
    public void come(Tx tx) {
        txMapper.insert(tx);
        AssertUtil.isOk(oneApi.come(MicroConstant.one), MicroConstant.one+": 远程服务异常");
        AssertUtil.isOk(twoApi.come(MicroConstant.two), MicroConstant.two+": 远程服务异常");
    }
}
