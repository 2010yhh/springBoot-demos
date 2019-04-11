package com.ctg.test.springbootnacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/9 23:55
 */
@Configuration
//加载 dataId 为 example 的配置源，并开启自动更新
//@NacosPropertySource(dataId = "example",autoRefreshed = true)
//加载不同groupId的配置
//@NacosPropertySource(dataId = "example", groupId ="test",autoRefreshed = true)
//加载不同dataId的配置
@NacosPropertySource(dataId = "example2",autoRefreshed = true)
public class AppConfig {
    @NacosValue(value = "${username:null}", autoRefreshed = true)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
