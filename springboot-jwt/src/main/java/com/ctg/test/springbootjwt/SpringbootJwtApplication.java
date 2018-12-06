package com.ctg.test.springbootjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *http://localhost:8090/webapp/login?userName=admin&passWord=123456
 *http://localhost:8090/webapp/getUser?token=****
 */
@SpringBootApplication
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }
}
