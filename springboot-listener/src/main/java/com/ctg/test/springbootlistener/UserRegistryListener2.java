package com.ctg.test.springbootlistener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
 public class UserRegistryListener2 {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Async//启用异步线程处理
    @EventListener
    public void onApplicationEvent(UserRegistryEvent2 event2) {
        logger.info(Thread.currentThread().getName()+":"+"监听到事件: " +"UserRegistryEvent2:"+event2.getUserName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}