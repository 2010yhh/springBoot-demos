package com.ctg.test.controller;

import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import com.ctg.test.util.DefaultProperies;
import com.ctg.test.util.DefaultProperies2;
import com.ctg.test.util.DefaultProperies3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *http://localhost:8090/test
 */
@Controller
@EnableAutoConfiguration
public class TestPropertyController {
	@Autowired
	UserService userService;
	@Autowired
	DefaultProperies defaultProperies;
   @Autowired
   DefaultProperies2 defaultProperies2;
	@Autowired
	private Environment env;
	@RequestMapping(value = {"/test"})
	@ResponseBody
	public Object test(
						HttpServletRequest request, HttpServletResponse response)  {
		Map<String,Object> result=new HashMap<>();
		result.put("DefaultProperies",defaultProperies);
		result.put("DefaultProperies2",defaultProperies2);
		DefaultProperies3 defaultProperies3=new DefaultProperies3();
		defaultProperies3.setUserName(env.getProperty("default.userName"));
		defaultProperies3.setPassWord(env.getProperty("default.passWord"));
		result.put("DefaultProperies3",defaultProperies3);
		return  result;
	}

}