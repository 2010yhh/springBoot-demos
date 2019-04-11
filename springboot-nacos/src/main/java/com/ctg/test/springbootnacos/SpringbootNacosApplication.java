package com.ctg.test.springbootnacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
/**
 * @Description:
 * http://localhost:8080/springboot-nacos/config/get
 * http://localhost:8080/springboot-nacos/discover/get?serviceName=UserService
 * nacos服务端的地址：localhost:8848
 * 调用open api添加配置:
 * 发布配置：curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=username=zyq"
 * 获取配置：curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP"
 * 注册服务：  curl -X PUT 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=UserService&ip=127.0.0.1&port=8080'
 * 查询服务：  curl -X GET http://localhost:8848/nacos/v1/ns/instance?serviceName=UserService&ip=127.0.0.1&port=8080
 * @Author: yanhonghai
 * @Date: 2019/3/27 21:45
 */


@SpringBootApplication
public class SpringbootNacosApplication {
    @NacosInjected
    private NamingService namingService;
    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;
    //curl -X PUT 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=xxx&ip=127.0.0.1&port=8080'
    @PostConstruct
    public void registerInstance() throws NacosException {
        if(CollectionUtils.isEmpty(namingService.getAllInstances(applicationName))){
            namingService.registerInstance(applicationName,"127.0.0.1",serverPort);
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosApplication.class, args);
    }

}
