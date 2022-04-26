package com.demo.css.kafka.consumer;

import cn.hll.tools.base.mod.Kv;
import cn.hll.tools.base.util.JsonUtil;
import cn.lalaframework.jms.kafka.consumer.thread.AbstractMessageThreadConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author Jas
 */
public class MsgConsumer extends AbstractMessageThreadConsumer {

    @KafkaListener(
//            topicPartitions = {@TopicPartition(topic = "css_reach", partitions = {"0","1","2","3","4","5"})},
            containerFactory = "consumer1",
            groupId = "${reach.group:}",
            concurrency = "${spring.kafka.multiple.consumer.consumer1.concurrency:1}",
            topics = "#{'${spring.kafka.multiple.consumer.consumer1.topics}'.split(',')}"
    )
    public void onMessage(ConsumerRecord record/*, Acknowledgment ack*/) {
        Kv kv = JsonUtil.toBean(record.value().toString(), Kv.class);
        System.out.println(String.format("接收完成：%s-%s-%s", record.partition(), record.offset(), kv.getVal()));
        handleMessage(record);
//        ack.acknowledge();
    }

    @Override
    public void handle(Object o) {
    }
}