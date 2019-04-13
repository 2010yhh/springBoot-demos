package com.ctg.test.springbootswagger.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * http://localhost:8080/test?userName=yhh&passWord=yhh
 *  swagger ui:  http://localhost:8080/swagger-ui.html
 *  json格式api文档：http://localhost:8080/v2/api-docs
 */
@Api(description = "TestController")
@Controller
public class TestController {
    //要定义paramType
    @ApiOperation(value = "test", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "密码",paramType = "query", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功", response = Map.class),
            @ApiResponse(code = 404, message = "请求资源未找到")
    }
    )
    @RequestMapping(value = {"/test"},method = RequestMethod.GET)
    @ResponseBody
    public Object test(@RequestParam String userName, @RequestParam String passWord,
                       HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("userName", userName);
        result.put("passWord", passWord);
        return result;
    }
}