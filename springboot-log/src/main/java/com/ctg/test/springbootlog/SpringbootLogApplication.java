package com.ctg.test.springbootlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot log配置：建议使用logback-spring.xml
 */
@SpringBootApplication
public class SpringbootLogApplication {
    private static    Logger LOGGER=LoggerFactory.getLogger(SpringbootLogApplication.class);
    public static void main(String[] args) {
        LOGGER.warn("SpringbootLogApplication begin start!");
        LOGGER.info("SpringbootLogApplication begin start!");
        LOGGER.error("SpringbootLogApplication begin start!");
        SpringApplication.run(SpringbootLogApplication.class, args);
        LOGGER.warn("SpringbootLogApplication start success!");
        LOGGER.info("SpringbootLogApplication start success!");
        LOGGER.error("SpringbootLogApplication start success!");
    }
}
