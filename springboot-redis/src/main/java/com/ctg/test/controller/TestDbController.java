package com.ctg.test.controller;

import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * redis结合db做缓存：
 * http://localhost:8090/testredis/user?userName=yhh&passWord=yhh
 * http://localhost:8090/testredis/user?(post put delete)
 */
@Controller
public class TestDbController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/user"}, method = {RequestMethod.GET})
    @ResponseBody
    public Object findUser(@RequestParam String userName, @RequestParam String passWord,
                           HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.findByUserName(userName, passWord);
        if (!StringUtils.isEmpty(user)) {
            result.put("code", "200");
            result.put("msg", "success");
            result.put("user", user);
        } else {
            result.put("code", "-1");
            result.put("msg", "error!");
        }
        return result;
    }

    @RequestMapping(value = {"/user"}, method = {RequestMethod.POST})
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = {"/user"}, method = {RequestMethod.PUT})
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = {"/user"}, method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteUser(@RequestParam int userId) {
        userService.deleteUser(userId);
    }
}