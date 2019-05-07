package com.ctg.test.security.listener;

import com.ctg.test.security.LoginEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/5/7 14:44
 */

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired
    private LoginEventService loginAttemptService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String userName= e.getAuthentication().getPrincipal().toString();
        logger.error("login fail222",e.getAuthentication().getPrincipal().toString());
        loginAttemptService.loginFailed(userName);
        logger.info("userName:{} login fail,fail counts:{}!",userName,loginAttemptService.getFailCounts(userName));

    }
}
