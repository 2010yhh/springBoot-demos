package com.ctg.test.springbootapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:基于Java bean的配置（推荐）,配置修改了，会自动更新
 * @Author: yanhonghai
 * @Date: 2019/4/8 23:33
 */
@Configuration
//默认的namespace,为application,相当于@EnableApolloConfig("application")
//@EnableApolloConfig
//@EnableApolloConfig("TEST1.Namespace1")
//@EnableApolloConfig("TEST1.Namespace2")
//注入多个namespace，并且指定顺序
//@EnableApolloConfig(value = {"FX.apollo", "application.yml"}, order = 1)
public class AppConfig  {
    @Value("${serviceTimeout:1000}")
    private int serviceTimeout;
    @Value("${serviceRetryTimes:3}")
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
