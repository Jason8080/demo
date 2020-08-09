package com.gm.demo.micro.server.mq.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * The interface One channel.
 *
 * @author Jason
 */
public interface OneChannel {

    String OUTPUT = "one-output";
    String INPUT = "one-input";

    @Output(OneChannel.OUTPUT)
    MessageChannel output();


    @Input(OneChannel.INPUT)
    SubscribableChannel input();

}