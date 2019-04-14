package com.ctg.test.springbootuserstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 自动配置的属性类
 * @Author: yanhonghai
 * @Date: 2019/4/14 10:05
 */
@ConfigurationProperties("com.ctg.test.user")
public class UserProperties {
    private String userName;
    private String passWord;

    @Override
    public String toString() {
        return "UserService{" +
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
