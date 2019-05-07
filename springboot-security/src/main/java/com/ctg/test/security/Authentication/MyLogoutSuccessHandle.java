package com.ctg.test.security.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.util.Result;
import com.ctg.test.util.ResultCode;
import com.ctg.test.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 自定义注销Handler，返回json
 * @Author: yanhonghai
 * @Date: 2019/5/4 11:22
 */
@Component
public class MyLogoutSuccessHandle implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result result = ResultUtil.success(ResultCode.SUCCESS, "注销成功！");
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
