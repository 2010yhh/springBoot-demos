package com.ctg.test.limit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * http://localhost:8080/limit/test1
 * http://localhost:8080/limit/test2
 * @Author: yanhonghai
 * @Date: 2019/4/16 12:36
 */
@Controller
public class TestController {
    @RateLimit(limitNum = 5.0)
    @RequestMapping("/test1")
    @ResponseBody
    public Object getResults() {
        return ResultUtil.success(200,"test1");
    }

    @RateLimit(limitNum = 10.0)
    @RequestMapping("/test2")
    @ResponseBody
    public Object getResultTwo(){
    Map<String,Object>map =new HashMap<>();

        return ResultUtil.success(200,"test2");
    }
}