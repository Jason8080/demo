package com.gm.demo.micro.server.mq.server.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class StreamProducer {
    @Autowired
    OneChannel oneChannel;


    public void sendMsg(String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        oneChannel.output().send(message);
    }
}
