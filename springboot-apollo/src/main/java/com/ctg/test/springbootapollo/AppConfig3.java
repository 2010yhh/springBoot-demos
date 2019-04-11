package com.ctg.test.springbootapollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:基于springboot的配置（推荐）,配置修改了，会自动更新
 * @Author: yanhonghai
 * @Date: 2019/4/8 23:33
 */
@Configuration
public class AppConfig3 {
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Value("${passWord}")
    private String passWord;

}
