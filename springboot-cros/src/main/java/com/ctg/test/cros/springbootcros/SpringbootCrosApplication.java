package com.ctg.test.cros.springbootcros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootCrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrosApplication.class, args);
    }
}
