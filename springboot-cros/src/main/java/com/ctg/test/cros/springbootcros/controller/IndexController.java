package com.ctg.test.cros.springbootcros.controller;


import com.ctg.test.cros.springbootcros.util.CookieUtils;
import com.ctg.test.cros.springbootcros.util.IpUtil;
import com.ctg.test.cros.springbootcros.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 启动2个后台进程，
 * server.port=8080
 * server.context-path=/webapp1
 * server.port=8090
 * server.context-path=/webapp2
 * 访问：http://localhost:8080/webapp1
 */
@Controller
@EnableAutoConfiguration
public class IndexController {
	@Autowired
	private Environment env;
	@Autowired
	IpUtil ipUtil;
	@RequestMapping("/")
	String index(HttpServletRequest request) {
		return "/index";
	}
	@RequestMapping("/index")
	String index2(HttpServletRequest request) {
		return "/index";
	}
	@RequestMapping("/test")
	@ResponseBody
	public  Object test(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result=new HashMap<>();
		String ip="";
		try {
			InetAddress inetAddress=ipUtil.getLocalHostLANAddress();
			ip=inetAddress.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("data","url:"+request.getRequestURL()+",ip: "+ip+",port:"+env.getProperty("server.port"));
		return ResultUtil.success(result);
	}
	@RequestMapping(value = "/setSession", method ={RequestMethod.POST,RequestMethod.GET} )
	@ResponseBody
	public Object  setSession(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("key") String key
			, @RequestParam("value") String value) {
		Map<String, Object> result = new HashMap<>();
		request.getSession().setAttribute(key, value);
		result.put("setSession-session_id", session.getId());
		result.put("session_key", key);
		result.put("session_value", value);
		CookieUtils.writeCookie(response,key,value);
		return ResultUtil.success(result);
	}

	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	@ResponseBody
	public Object getSession(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("key") String key) {
		Map<String, Object> result = new HashMap<>();
		result.put("getSession-session_id", session.getId());
		result.put("session_key", key);
		result.put("cookie获取session_value", CookieUtils.getCookie(request,key));
		return ResultUtil.success(result);
	}
}