package com.ctg.test.springbootauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端(不同用户请求)
 * --->资源
 * --->授权服务器
 * --->资源服务器
 * 授权码模式；简化模式；密码模式；客户端模式
 */
@SpringBootApplication
public class SpringbootAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAuthApplication.class, args);
    }
}
