package com.ctg.test.springbootconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/3/30 16:49
 */
@Component
@ConfigurationProperties(prefix="com.ctg.test")
public class UserInfo {
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
