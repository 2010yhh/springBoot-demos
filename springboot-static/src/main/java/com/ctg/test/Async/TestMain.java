package com.ctg.test.Async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/11/8 18:38
 */
public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AsyncTaskService.class);
        AsyncTaskService asyncTaskService=context.getBean(AsyncTaskService.class);
        for(int i=0;i<10;i++){
            asyncTaskService.execute(i);
        }
        context.close();
    }
}
