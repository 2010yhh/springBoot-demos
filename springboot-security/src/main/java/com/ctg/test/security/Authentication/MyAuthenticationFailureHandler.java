package com.ctg.test.security.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.util.Result;
import com.ctg.test.util.ResultCode;
import com.ctg.test.util.ResultUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 自定义登录失败Handler，返回json
 * @Author: yanhonghai
 * @Date: 2019/4/15 0:22
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        StringBuffer sb = new StringBuffer();
        Result result = new Result();
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = ResultUtil.error(ResultCode.ERROR, "用户名或密码输入错误，登录失败!");
        } else if (e instanceof DisabledException) {
            result = ResultUtil.error(ResultCode.ERROR, "账户被禁用，登录失败，请联系管理员!");
        } else {
            result = ResultUtil.error(ResultCode.ERROR, "登录失败:" + e.fillInStackTrace());
        }
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
