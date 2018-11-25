package com.ctg.test.springboothtml.controller;

import com.ctg.test.springboothtml.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 *http://localhost:8090
 *http://localhost:8090/test
 *静态资源：http://localhost:8090/static/img/1.jpg
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
	String test(HttpServletRequest request) {
		String ip="";
		try {
			InetAddress inetAddress=ipUtil.getLocalHostLANAddress();
			ip=inetAddress.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "From "+ip+":"+env.getProperty("server.port");
	}
}