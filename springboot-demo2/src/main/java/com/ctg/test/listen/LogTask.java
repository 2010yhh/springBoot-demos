package com.ctg.test.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTask implements Runnable{
private final static Logger log = LoggerFactory.getLogger(LogTask.class); 
	@Override
	public void run() {
		log.info("--->ScheduledLogTask:begin collect log");
	}
}
