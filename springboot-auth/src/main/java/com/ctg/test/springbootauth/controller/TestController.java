package com.ctg.test.springbootauth.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 1.测试需要启动redis，
 * 2.直接get访问localhost:8080/public/1  localhost:8080/private/1
 * 3.使用http工具post访问password模式http://localhost:8080/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456
 * 使用http工具post访问client模式http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456
 * 4.再次get访问http://localhost:8080/private/1?access_token=bd77315b-5f83-433f-a4aa-b9f20b89ff34
 * http://localhost:8080/private/1?access_token=d6912dd2-347d-4e64-98a3-922380dab7a0
 * @Author: yanhonghai
 * @Date: 2019/4/18 0:31
 */
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/public/{id}")
    public String getProduct(@PathVariable String id) {
        logger.info("当前用户认证信息：{}", JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication()));
        return "public id: " + id;
    }

    @GetMapping("/private/{id}")
    public String getOrder(@PathVariable String id) {
        logger.info("当前用户认证信息：{}", JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication()));
        return "private id : " + id;
    }

}
