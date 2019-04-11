package com.ctg.test.springbootapollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * localhost:8050/springboot-apollo/config/get
 * @Author: yanhonghai
 * @Date: 2019/3/27 21:45
 */
@Controller
@RequestMapping(value = "/config")
public class TestController {
    @Autowired
    AppConfig appConfig;

    @Autowired
    AppConfig2 appConfig2;

    @Autowired
    AppConfig3 appConfig3;

//    @ApolloConfig
//    private Config config;
//    @ApolloConfig("TEST1.Namespace1")
//    private Config config2;

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object test(){
        Map<String,Object>map =new HashMap<>();
        map.put("appConfig.getServiceTimeout()：",appConfig.getServiceTimeout());
        map.put("appConfig2.getServiceTimeout()：",appConfig2.getServiceTimeout());
        map.put("appConfig3.getPassWord()：",appConfig3.getPassWord());
        //map.put("config.getIntProperty(\"serviceTimeout\", 100)",config.getIntProperty("serviceTimeout", 100));
        //map.put("config2.getIntProperty(\"serviceTimeout\", 100)",config2.getIntProperty("serviceTimeout", 100));
        return map;
}
}
