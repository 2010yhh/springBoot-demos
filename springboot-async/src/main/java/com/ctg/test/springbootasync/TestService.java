package com.ctg.test.springbootasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/20 1:02
 */
@Service
public class TestService {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    //@Async
    @Async("taskExecutor")
    public void test(){
        logger.info(Thread.currentThread().getName()+".........2");
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        logger.info(Thread.currentThread().getName()+".........3");
    }
}
