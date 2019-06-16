package com.ctg.test.util;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/28 11:02
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="default")
@PropertySource("classpath:application.properties")
public class DefaultProperies2 {
    public  String userName;
    public String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
