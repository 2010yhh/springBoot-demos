package com.ctg.test.springbootlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * http://localhost:8080/test
 * http://localhost:8080/test2
 * @Author: yanhonghai
 * @Date: 2019/4/20 0:09
 */
@RestController
public class TestController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplicationContext publisher;
    @GetMapping("/test")
    public void test() {

            UserRegistryEvent event = new UserRegistryEvent(this, "user1");
            publisher.publishEvent(event);
            logger.info(Thread.currentThread().getName()+"UserRegistryEvent事件发布：user1"+"注册了");
    }
    //异步事件监听不用等事件监听器处理完逻辑就返回，并且线程不一样
    @GetMapping("/test2")
    public void test2() {
         UserRegistryEvent2 event2 = new UserRegistryEvent2(this, "user2");
            publisher.publishEvent(event2);
            logger.info(Thread.currentThread().getName()+":"+"UserRegistryEvent2事件发布：user2"+"注册了");
    }
}
