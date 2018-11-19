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
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyQuartzJobBean2 extends QuartzJobBean {
    protected Logger logger = LoggerFactory.getLogger(MyQuartzJobBean2.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        logger.info("....execute2:{}....", context.getTrigger().getKey());
    }

}
