package com.ctg.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * durid web监控台：http://localhost:8090/druid/index.html
 * web: http://localhost:8090
 *   http://localhost:8090/#/login
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ctg.test.mapper")//配置mybatis包扫描
@EnableCaching
public class ShiroApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShiroApplication.class, args);
    }
}
