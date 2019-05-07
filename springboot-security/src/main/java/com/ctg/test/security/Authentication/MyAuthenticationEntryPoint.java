package com.ctg.test.security.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.util.Result;
import com.ctg.test.util.ResultCode;
import com.ctg.test.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义未登陆时Handler，返回json
 * @Author: yanhonghai
 * @Date: 2019/5/4 14:10
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = ResultUtil.error(ResultCode.ERROR, "需要权限,请先登录！");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
    }
}
