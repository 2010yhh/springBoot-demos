package com.ctg.test.springbootelk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootElkApplication {
    private static Logger LOGGER= LoggerFactory.getLogger(SpringbootElkApplication.class);
    public static void main(String[] args) {
        LOGGER.info("SpringbootElkApplication is start.....");
        SpringApplication.run(SpringbootElkApplication.class, args);

    }

}
