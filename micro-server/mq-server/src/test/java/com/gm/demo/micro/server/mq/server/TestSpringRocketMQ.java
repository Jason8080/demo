package com.gm.demo.micro.server.mq.server;

import com.gm.demo.micro.server.mq.server.rocket.RocketProducer;
import com.gm.demo.micro.server.mq.server.stream.OneChannel;
import com.gm.demo.micro.server.mq.server.stream.StreamProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableBinding({OneChannel.class})
public class TestSpringRocketMQ {

    @Autowired
    private RocketProducer rocketProducer;
    @Autowired
    private StreamProducer streamProducer;



    @Test
    public void testSendMsg(){
        String msg = "我的第1个SpringRocketMQ消息!";
//        rocketProducer.sendMsg("one", msg);
        streamProducer.sendMsg(msg);
        System.out.println("发送成功");
    }


}
