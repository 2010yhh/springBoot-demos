package com.ctg.test.springbootelk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: localhost:8090/springboot-elk/test
 * @Author: yanhonghai
 * @Date: 2019/3/27 21:45
 */
@RestController
public class TestController {
   private static Logger LOGGER= LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(){
        for(int i=0;i<5;i++){
            LOGGER.info("test info:{}",i);
        }
        LOGGER.error("test error");
        Map<String,Object>map =new HashMap<>();
        map.put("code",200);
        map.put("resilt","test:"+new Date().toString());
        return map;
}
}
