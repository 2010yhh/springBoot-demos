package com.ctg.test.durid.controller;

import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.service.UserService;
import org.apache.commons.lang.exception.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:测试durid连接池
 * @Author: yanhonghai
 * @Date: 2018/9/17 1:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.addUser(user);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
    @RequestMapping(value = "/add2",method = RequestMethod.POST)
    @ResponseBody
    public Object addUser2(@RequestParam String userName,@RequestParam String passWord) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user=new User();
            user.setUserName(userName);
            user.setPassWord(passWord);
            userService.addUser(user);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.deleteUser(id);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
    /**
     * http://localhost:8090/user?userName=admin
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object getUser(@RequestParam String userName,@RequestParam String passWord) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.findByUserName(userName);
            result.put("code", "200");
            result.put("msg", "success");
            result.put("result", user);
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.updateUser(user);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
    @RequestMapping(value = "/update2",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateUser2(@RequestParam Integer id,@RequestParam String userName,@RequestParam String passWord) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user=new User();
            user.setId(id);
            user.setUserName(userName);
            user.setPassWord(passWord);
            userService.updateUser(user);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            result.put("code", "201");
            result.put("msg", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }
}