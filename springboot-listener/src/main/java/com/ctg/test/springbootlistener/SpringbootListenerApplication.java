package com.ctg.test.springbootlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootListenerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootListenerApplication.class);
        springApplication.run(args);

    }
}
