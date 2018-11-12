package com.ctg.test.listen;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听器的作用：可以在listen中完成一些如数据库、创建、数据库加载等一些初始化操作
 */
@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	public static AtomicInteger count=new AtomicInteger(0);
	 protected Logger log = LoggerFactory.getLogger(StartApplicationListener.class); 
	 private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    @Override  
	    public void onApplicationEvent(ContextRefreshedEvent event) {  
	    	//防止重复执行
	    	 if (event.getApplicationContext().getParent() == null && count.incrementAndGet()<=1) {
	    		 //这里一个定时任务的初始化
	 	        this.service.scheduleAtFixedRate(new LogTask(),1000, 1000*60,TimeUnit.MILLISECONDS );
	 	        log.info("Listener系统配置加载完成...");  
	    	 }
	       
	    }  
}
