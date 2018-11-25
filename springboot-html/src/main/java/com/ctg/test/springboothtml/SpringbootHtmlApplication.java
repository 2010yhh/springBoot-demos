package com.ctg.test.springboothtml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootHtmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHtmlApplication.class, args);
    }
}
