package com.gm.demo.micro.server.mq.server.rocket;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
@RocketMQMessageListener(topic = "one", consumerGroup = "one")
public class RocketConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }
}