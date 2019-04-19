package com.ctg.test.springbootlistener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
 public class UserRegistryListener {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void onApplicationEvent(UserRegistryEvent event) {
        logger.info(Thread.currentThread().getName()+":"+"监听到事件: " +"UserRegistryEvent:"+event.getUserName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}