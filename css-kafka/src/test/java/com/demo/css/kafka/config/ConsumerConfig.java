package com.demo.css.kafka.config;


import com.demo.css.kafka.consumer.MsgConsumer;
import com.demo.css.kafka.provider.MsgProducer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(MsgConsumer.class)
@ComponentScan(basePackages = {"com.demo.css.kafka.consumer", "cn.hll.tools"})
public class ConsumerConfig {
}
