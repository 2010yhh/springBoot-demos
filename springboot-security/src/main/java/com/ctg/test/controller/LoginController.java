package com.ctg.test.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 简单测试用login_page.html;整合vue只用index.html和static下的静态资源(为前端打包后的文件)
 * localhost:8080
 * localhost:8080/index
 * localhost:8080/mylogin
 * @Author: yanhonghai
 * @Date: 2019/4/13 15:46
 */
@Controller
public class LoginController {

    @RequestMapping("/mylogin")
    public String mylogin() {
        //简单测试用login_page.html;vue 只用index.html
        return "index";
    }

    @RequestMapping("/")
    public String mylogin2() {
        //简单测试用login_page.html;vue 只用index.html
        return "index";
    }

    @RequestMapping("/index")
    public String mylogin3() {
        //简单测试用login_page.html;vue 只用index.html
        return "index";
    }

    /**
     * 获取当前登录用户的信息
     * http://localhost:8080/test/userInfo
     *
     * @return
     */
    @RequestMapping(value = {"/userInfo"})
    @ResponseBody
    public Object getUserInfo() {
        Map<String, Object> result = new HashMap<>();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("userInfo", userDetails);
        return result;
    }

    /**
     * 配置了自定义登入登出Handler，优先响应登入登出Handler(本代码是返回json给前端处理)，
     * 这里的后端重定向设置不起效果
     *
     * @return
     */
    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/fail")
    public String error() {
        return "fail";
    }
}