package com.ctg.test.springboothtml.controller;

import com.ctg.test.springboothtml.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 启动3个后台进程，端口号分别为：8080,8090,8100，nginx端端口号设置为：8060,8070
 *http://localhost:8080/webapp1
 *http://localhost:8080/webapp1/test
 *静态资源：http://localhost:8080/webapp1/static/img/1.jpg
 * 测试nginx静态资源和nginx负载均衡：
 * http://192.168.159.142:8060/webapp1
 * 或者直接访问：
 * http://192.168.159.142:8060/webapp1/test/static/img/1.jpg
 * http://192.168.159.142:8060/webapp1/test
 * 测试nginx不同端口，一个端口不同上下文
 * http://192.168.159.142:8070/webapp2/
 * http://192.168.159.142:8070/webapp2/test
 * 测试nginx一个端口不同上下文
 * http://192.168.159.142:8070/webapp2/
 * http://192.168.159.142:8070/webapp2/test
 * http://192.168.159.142:8070/webapp3/
 * http://192.168.159.142:8070/webapp3/test
 *
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
		result.put("code","200");
		String ip="";
		try {
			InetAddress inetAddress=ipUtil.getLocalHostLANAddress();
			ip=inetAddress.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("data","url:"+request.getRequestURL()+",ip: "+ip+",port:"+env.getProperty("server.port"));
         return result;
	}
}