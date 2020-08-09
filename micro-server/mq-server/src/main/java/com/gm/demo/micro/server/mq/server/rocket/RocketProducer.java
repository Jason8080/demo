package com.gm.demo.micro.server.mq.server.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Component
public class RocketProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    public void sendMsg(String topic, String msg) {
        rocketMQTemplate.convertAndSend(topic, msg);
    }


}