package com.ctg.test.kafaktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * tar zxvf kafka_2.11-2.1.1.tgz
 * 启动zk:
 * 启动kafak borker:bin/kafka-server-start.sh config/server.properties
 * 生产命令：bin/kafka-console-producer.sh --broker-list localhost:9092 --topic TestTopicbin/kafka-console-producer.sh --broker-list localhost:9092 --topic TestTopic
 *消费命令：bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TestTopic --from-beginning
 * */
@SpringBootApplication
public class SpringbootKafakTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafakTestApplication.class, args);
    }
}
