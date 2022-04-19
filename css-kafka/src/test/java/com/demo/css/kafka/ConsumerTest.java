package com.demo.css.kafka;


import cn.lalaframework.utils.Constants;
import com.demo.css.kafka.config.ConsumerConfig;
import com.demo.css.kafka.consumer.MsgConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

@ActiveProfiles("dev")
@Import(ConsumerConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumerTest.class)
@ComponentScan(value = {"com.demo.css.kafka.consumer", "cn.hll.tools"})
public class ConsumerTest {

    static {
        System.setProperty(Constants.KEY_HLL_ENV, "dev");
        System.setProperty(Constants.KEY_HLL_APP_ID, "css-reach-infra");
        System.setProperty(Constants.KEY_CONSUL_HOST, "consul-dev.myhll.cn");
        System.setProperty(Constants.KEY_CONSUL_PORT, "80");
        System.setProperty(Constants.KEY_CONSUL_TOKEN, "616bb402-e250-2d7a-668e-8d1540556907");
        System.setProperty("apollo.meta", "http://apollo-new-dev.myhll.cn:8080");
    }

    @Resource
    private MsgConsumer msgConsumer;

    @Test
    public void test() throws IOException {
        while (System.in.read() != 'q') {
            System.out.println("退出请输入q!");
        }
    }
}
