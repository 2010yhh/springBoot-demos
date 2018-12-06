package com.ctg.test.springbootjwt.controller;

import com.ctg.test.springbootjwt.utils.JwtUtil;
import com.ctg.test.springbootjwt.filter.User;
import com.ctg.test.springbootjwt.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *登录后，生产jwt返回给客户端信息，(服务端不存储jwt)
 * 访问其他接口时，以jwt作为token参数请求
 * token过期时间设置短些，token放在头部，并且用的是https
 */
@Controller
@EnableAutoConfiguration
public class TestController {
	@Autowired
	private Environment env;
	@RequestMapping("/testJwt")
	@ResponseBody
	public Object testJwt(HttpServletRequest request, HttpServletResponse response) {
		String ip="";
		return "From "+ip+":"+env.getProperty("server.port");
	}

	/**
	 * 测试登录接口

	 * @return
	 */
	@RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseData login(@RequestParam String userName, @RequestParam String passWord) {
		//查询数据库
		if ("admin".equals(userName) && "123456".equals(passWord)) {
			ResponseData responseData = ResponseData.ok();
			User user = new User();
			user.setId("1");
		    user.setUserName(userName);
			String token= null;
			try {
				token = JwtUtil.createToken(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (token != null) {
				responseData.putDataValue("token", token);
			}
			return responseData;
		}
		return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY,"用户名或者密码");
	}

	/**
	 * 测试需要token的接口
	 * @param token
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public ResponseData getInfo(@RequestParam String token) {
		User user = null;
		try {
			user = JwtUtil.getUser(token);
		} catch (Exception e) {

				ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
		}
		if (user != null) {
			return ResponseData.ok().putDataValue("user", user);
		}
		return ResponseData.customerError().putDataValue(ResponseData.ERRORS_KEY, "token校验失败, Token已过期");
	}
}