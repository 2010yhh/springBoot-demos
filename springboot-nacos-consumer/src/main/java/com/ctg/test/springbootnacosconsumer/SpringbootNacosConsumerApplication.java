package com.ctg.test.springbootnacosconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通过nacos，发现、调用其他服务
 */
@SpringBootApplication
public class SpringbootNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosConsumerApplication.class, args);
    }

}
