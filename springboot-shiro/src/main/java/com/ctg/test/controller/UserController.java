package com.ctg.test.controller;

import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Author: yanhonghai
 * @Date: 2018/9/17 1:00
 * @RequiresRoles(value={"admin","user"},logical = Logical.OR)
 * @RequiresPermissions(value={"add","update"},logical = Logical.AND)
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
    /**
     * 测试@RequiresRoles
     * http://localhost:8090/user/testRequiresRoles
     *user1 user2 admin用户测试即可,密码都是123456
     * @return
     */
    @RequiresRoles({"admin"})
    @RequestMapping(value = {"/testRequiresRoles"})
    @ResponseBody
    public Object testRequiresRoles() {
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

    /**
     * 测试@RequiresPermissions
     * http://localhost:8090/user/testRequiresPermissions
     * user1 user2 admin用户测试即可,密码都是123456
     *
     * @return
     */
    @RequestMapping(value = {"/testRequiresPermissions"})
    @ResponseBody
    @RequiresPermissions("delete")
    public Object testRequiresPermissions() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findAll();
        result.put("code", "200");
        result.put("msg", "success");
        result.put("result", users);
        return result;
    }

    /**
     * http://localhost:8090/user/userInfo
     *
     * @return
     */
    @RequestMapping(value = {"/userInfo"})
    @ResponseBody
    public Object user() {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        result.put("subject.getPrincipal()", subject.getPrincipal());
        result.put("subject.getPrincipals()", subject.getPrincipals());
        // 用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);
        result.put("RememberMe:", subject.isRemembered());
        return result;
    }
}