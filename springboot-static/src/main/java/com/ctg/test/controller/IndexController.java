package com.ctg.test.controller;

import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import com.ctg.test.util.DefaultProperies;
import com.ctg.test.util.DefaultProperies2;
import com.ctg.test.util.DefaultProperies3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *http://localhost:8090/login?userName=yhh&passWord=yhh
 */
@Controller
@EnableAutoConfiguration
public class IndexController {
	@Autowired
	UserService userService;
	@RequestMapping(value = {"/login"})
	@ResponseBody
	public Object login(@RequestParam String userName, @RequestParam String passWord,
						HttpServletRequest request, HttpServletResponse response)  {
		Map<String,Object> result=new HashMap<>();
		List<User>users=userService.findByUserName(userName,passWord);
		if(!CollectionUtils.isEmpty(users)){
			result.put("code","200");
			result.put("msg","success");
			result.put("user",users);
		}else
		{
			result.put("code","-1");
			result.put("msg","error!");
		}
		return  result;
	}
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
	@RequestMapping("/index2")
	String index2(HttpServletRequest request) {
		// 逻辑处理
		request.setAttribute("key", "index");
		return "/index2";
	}
	@RequestMapping("/")
	String index(HttpServletRequest request) {
		return "/index";
	}
}