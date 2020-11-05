package com.gm.common.dynamic;

import com.gm.common.dynamic.dao.entity.Tx;
import com.gm.common.dynamic.service.TxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TxMapperTest {

    @Autowired
    TxService txService;

    @Test
    public void save() {
        Tx tx = new Tx();
        tx.setStr("666");
        txService.save(tx);
    }
}