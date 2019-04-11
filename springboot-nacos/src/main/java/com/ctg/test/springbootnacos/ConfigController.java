package com.ctg.test.springbootnacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    AppConfig appConfig;

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object test(){
        Map<String,Object>map =new HashMap<>();
        map.put("username",appConfig.getUsername());
        return map;
}
}
