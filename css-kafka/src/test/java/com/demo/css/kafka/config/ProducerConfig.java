package com.demo.css.kafka.config;


import com.demo.css.kafka.provider.MsgProducer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(MsgProducer.class)
@ComponentScan(basePackages = {"com.demo.css.kafka.provider", "cn.hll.tools"})
public class ProducerConfig {
}
