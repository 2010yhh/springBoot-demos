package com.ctg.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBoot基础security做单机登录系统： 认证（你是谁）
 * 授权（你能干什么）
 * 攻击防护（防止伪造身份）
 * Authentication（认证）和 Authorization（授权，也叫访问控制）
 * durid web监控台：http://localhost:8080/druid/index.html
 * web: http://localhost:8080
 * http://localhost:8080/index
 */

@SpringBootApplication
@MapperScan("com.ctg.test.mapper")//配置mybatis包扫描
public class SpringbootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityApplication.class, args);
    }

}
