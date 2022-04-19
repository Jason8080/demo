package com.demo.css.kafka;


import cn.hll.tools.base.mod.Kv;
import cn.hll.tools.base.util.ExceptionUtil;
import cn.hll.tools.base.util.ThreadUtil;
import cn.lalaframework.utils.Constants;
import com.demo.css.kafka.config.ProducerConfig;
import com.demo.css.kafka.provider.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@ActiveProfiles("stg")
@Import(ProducerConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderTest.class)
public class ProviderTest {

    static {
        System.setProperty(cn.lalaframework.utils.Constants.KEY_HLL_ENV, "stg");
        System.setProperty(cn.lalaframework.utils.Constants.KEY_HLL_APP_ID, "css-reach-infra");
        System.setProperty(cn.lalaframework.utils.Constants.KEY_CONSUL_HOST, "consul-stg.myhll.cn");
        System.setProperty(cn.lalaframework.utils.Constants.KEY_CONSUL_PORT, "80");
        System.setProperty(Constants.KEY_CONSUL_TOKEN, "2c9d32f5-015e-d9aa-d777-0851b5f7fb3e");
        System.setProperty("apollo.meta", "http://apollo-stg.myhll.cn:8080");
    }

    @Resource
    private MsgProducer msgProducer;


    @Test
    public void test() throws IOException {
        AtomicInteger i = new AtomicInteger();
        while (System.in.read() != 'q') {
            System.out.println(String.format("发送成功: %s", i.getAndIncrement()));
            ThreadUtil.execute(() -> msgProducer.send(new Kv("ID", i.get())));
        }
    }
}
