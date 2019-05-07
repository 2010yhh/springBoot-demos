package com.ctg.test.controller;


import com.alibaba.fastjson.JSON;
import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * http://localhost:8080/user/list
     * //此方法只允许 ROLE_ADMIN角色访问
     * @return
     */

    //@Secured({"ROLE_ADMIN"})，直接在WebSecurityConfig配中中配置
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findAll();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("result", users);
        return result;
    }
    //@Secured({"ROLE_MANAGER"})//此方法只允许ROLE_MANAGER角色访问，直接在WebSecurityConfig配中中配置
    @RequestMapping(value = {"/list2"}, method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList2() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findAll();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("result", users);
        return result;
    }
    @RequestMapping(value = {"/list3"}, method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList3() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.findAll();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("result", users);
        return result;
    }
    /**
     * 获取当前登录用户的信息
     * http://localhost:8080/user/userInfo
     *Spring Security使用一个Authentication对象来描述当前用户的相关信息。
     * SecurityContextHolder中持有的是当前用户的SecurityContext，
     * 而SecurityContext持有的是代表当前用户相关信息的Authentication的引用
     * 利用SecurityContextHolder.getContext().setAuthentication(newAuth)可以进行动态修改权限
     * @return
     */
    @RequestMapping(value = {"/info"})
    @ResponseBody
    public Object getUserInfo() {
        Map<String, Object> result = new HashMap<>();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("result", JSON.toJSONString(userDetails));
        return result;
    }
    @RequestMapping(value = {"/roles"})
    @ResponseBody
    public Object getUserInfoRoles() {
        Map<String, Object> result = new HashMap<>();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Collection<? extends GrantedAuthority> grantedAuthoritys=userDetails.getAuthorities();
        Set<String > roleNames=new HashSet<>();
        for(GrantedAuthority grantedAuthority:grantedAuthoritys){
            roleNames.add(grantedAuthority.getAuthority());
        }
        result.put("code", 200);
        result.put("msg", "success");
        result.put("result", roleNames);
        return result;
    }
}