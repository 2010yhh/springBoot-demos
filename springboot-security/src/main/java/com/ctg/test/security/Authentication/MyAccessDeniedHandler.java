package com.ctg.test.security.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.ctg.test.util.Result;
import com.ctg.test.util.ResultCode;
import com.ctg.test.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义权限不足Handler，返回json
 * @Author: yanhonghai
 * @Date: 2019/5/4 14:12
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Result result = ResultUtil.error(ResultCode.ERROR, "权限不足，无法访问！");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
    }
}