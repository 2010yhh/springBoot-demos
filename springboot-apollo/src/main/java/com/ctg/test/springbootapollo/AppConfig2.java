package com.ctg.test.springbootapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 使用@ConfigurationProperties注入
 * @Author: yanhonghai
 * @Date: 2019/4/8 23:33
 */

//配置修改了，不会自动更新，需要做其他修改来支持自动更新
@ConfigurationProperties(prefix = "")
@Configuration
//默认的namespace,为application
//@EnableApolloConfig
public class AppConfig2  {
    private int serviceTimeout;
    private int serviceRetryTimes;
    public int getServiceTimeout() {
        return serviceTimeout;
    }

    public void setServiceTimeout(int serviceTimeout) {
        this.serviceTimeout = serviceTimeout;
    }

    public int getServiceRetryTimes() {
        return serviceRetryTimes;
    }

    public void setServiceRetryTimes(int serviceRetryTimes) {
        this.serviceRetryTimes = serviceRetryTimes;
    }

}
