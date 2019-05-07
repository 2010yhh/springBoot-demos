package com.ctg.test.security.listener;

import com.ctg.test.security.LoginEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/5/7 14:43
 */

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginEventService loginAttemptService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        UserDetails userDetails  =(UserDetails)e.getAuthentication().getPrincipal();
        logger.info("userName:{} login success!",userDetails.getUsername());
        loginAttemptService.loginSucceeded(userDetails.getUsername());
    }
}