package com.gm.demo.micro.server.mq.server.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */
@Service
public class StreamConsumer {

    @StreamListener(OneChannel.INPUT)
    public void receiveInput(Message<String> message) {
        System.out.println("Receive input: " + message);
    }
}
