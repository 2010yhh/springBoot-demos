package com.ctg.test.quartz.config;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description: Quartz job类
 * @Author: yanhonghai
 * @Date: 2018/11/16 14:47
 */
@PersistJobDataAfterExecution//持久化
@DisallowConcurrentExecution//禁止并发执行(Quartz不要并发地执行同一个job定义（这里指一个job类的多个实例）)
public class MyQuartzJobBean extends QuartzJobBean {
    protected Logger logger = LoggerFactory.getLogger(MyQuartzJobBean.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        logger.info("....execute:{}....", context.getTrigger().getKey());
    }

}
