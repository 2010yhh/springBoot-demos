package com.ctg.test.kafaktest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/16 15:29
 */
public class ConsumerListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @KafkaListener(topics = {"TestTopic"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka消息的key: " + record.key());
        logger.info("kafka消息的value: " + record.value().toString());
    }
}
