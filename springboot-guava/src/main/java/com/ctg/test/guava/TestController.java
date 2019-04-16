package com.ctg.test.guava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * http://localhost:8080/test1?userName=123
 * http://localhost:8080/test2?userName=123
 * @Author: yanhonghai
 * @Date: 2019/4/16 12:36
 */
@Controller
public class TestController {
    @Autowired
    CommonCacheService commonCacheService;
    @RequestMapping("/test1")
    @ResponseBody
    public Object getResults(String userName) {
        commonCacheService.getCache().put("userName",userName);
        return ResultUtil.success(200,commonCacheService.getCache().asMap().values());
    }

    @RequestMapping("/test2")
    @ResponseBody
    public Object getResultTwo(){
        return ResultUtil.success(200,commonCacheService.getCache().asMap().values());
    }
}