package com.ctg.test.springbootconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/3/30 16:49
 */
@Component
//指定配置路径
@PropertySource("classpath:conf/user.properties")
@ConfigurationProperties(prefix="test")
public class UserInfo2 {
    private String userName;
    private String passWord;
    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

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
