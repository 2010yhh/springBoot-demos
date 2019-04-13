package com.ctg.test.springbootconfig.controller;

import com.ctg.test.springbootconfig.UserInfo;
import com.ctg.test.springbootconfig.UserInfo2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * localhost:8090/springboot-config/test
 * 指定-Dserver.port=5555 后，http://localhost:5555/springboot-config/test
 * @Author: yanhonghai
 * @Date: 2019/3/27 21:45
 */
@Controller
public class TestController {
   private static Logger LOGGER= LoggerFactory.getLogger(TestController.class);
    @Value("${com.ctg.test.userName}")
    private String userName;
    @Value("${com.ctg.test.passWord}")
    private String passWord;
    @Autowired
    private Environment environment;
    @Autowired
    private UserInfo userInfo ;
    @Autowired
    private UserInfo2 userInfo2 ;
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(){
        Map<String,Object>map =new HashMap<>();
        map.put("code",200);
        map.put("@Value读取：",userName+":"+passWord);
        map.put("environment读取：",environment.getProperty("com.ctg.test.userName")+":"+environment.getProperty("com.ctg.test.passWord"));
        map.put("Bean读取：",userInfo.getUserName()+":"+userInfo.getPassWord());
        map.put("Bean读取指定配置路径：",userInfo2.getUserName()+":"+userInfo2.getPassWord());
        return map;
}
}
