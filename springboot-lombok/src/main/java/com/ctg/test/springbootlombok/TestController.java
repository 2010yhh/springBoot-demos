package com.ctg.test.springbootlombok;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/18 20:41
 */
@Controller
@Slf4j
public class TestController {
    @RequestMapping(value = {"/test"})
    @ResponseBody
    public Object getUserInfo() {
       log.info("test");
       return "test";
    }
}
