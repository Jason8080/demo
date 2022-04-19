package com.demo.css.kafka.provider;

import cn.hll.tools.base.util.JsonUtil;
import cn.lalaframework.jms.core.producer.MessageProducer;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * .
 *
 * @author Jas °
 * @date 2021 /8/6 (周五)
 */
public class MsgProducer {
    /**
     * 使用多源kafka，由于Bean是动态注入，此处需要懒加载模式
     * 对应配置中心sources的producer1
     */
    @Lazy
    @Resource(name = "producer1")
    private MessageProducer messageProducer;

    /**
     * 发送消息.
     *
     * @param obj the obj
     */
    public void send(Object obj) {
        messageProducer.sendMessage(JsonUtil.toJson(obj));
    }
}
