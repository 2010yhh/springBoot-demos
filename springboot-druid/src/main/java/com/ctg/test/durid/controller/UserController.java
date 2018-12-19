package com.ctg.test.durid.controller;

import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.service.UserService;
import org.apache.commons.lang.exception.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Author: yanhonghai
 * @Date: 2018/9/17 1:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    /**
     * http://localhost:8090/user/testDb
     * @return
     */
    @RequestMapping(value = {"/testDb"})
    @ResponseBody
    public Object testDb() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> users = userService.findAll();
            result.put("code", "200");
            result.put("msg", "success");
            result.put("result", users);
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
}