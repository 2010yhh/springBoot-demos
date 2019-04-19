package com.ctg.test.springbootasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * springboot异步操作可以使用@EnableAsync和@Async两个注解，本质就是多线程和动态代理
 * 异步方法使用注解@Async ,返回值为void或者Future
 * 异步方法和调用方法一定要写在不同的类中,如果写在一个类中是没有效果的
 * @Author: yanhonghai
 * @Date: 2019/4/20 0:09
 */
@RestController
public class TestController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestService testService;
    @GetMapping("/test")
    public void test() {
        logger.info(Thread.currentThread().getName()+".........1");
        testService.test();
        logger.info(Thread.currentThread().getName()+".........4");
    }

    @GetMapping("/test2")
    public void test2() {
     }
}
