package com.ctg.test.springboottkmybatis.controller;


import com.ctg.test.springboottkmybatis.model.User;
import com.ctg.test.springboottkmybatis.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页测试：
 * http://localhost:8090/all
 * http://localhost:8090/all?pageNum=2&pageSize=5
 */
@Controller
public class TestDbController {
	@Autowired
	UserService userService;
	/**
	 * 测试增删改
	 */
	@RequestMapping(value = {"/user"},method={RequestMethod.GET})
	@ResponseBody
	public Object findUser(@RequestParam String userName, @RequestParam String passWord,
						HttpServletRequest request, HttpServletResponse response)  {
		Map<String,Object> result=new HashMap<>();
		List<User>users=userService.findByUserName(userName,passWord);
		if(!CollectionUtils.isEmpty(users)){
			result.put("code","200");
			result.put("msg","success");
			result.put("user",users.get(0));
		}else
		{
			result.put("code","-1");
			result.put("msg","error!");
		}
		return  result;
	}
	@RequestMapping(value = {"/user"},method={RequestMethod.POST})
	@ResponseBody
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	@RequestMapping(value = {"/user"},method={RequestMethod.PUT})
	@ResponseBody
	public void updateUser(@RequestBody User user) {
		List<User>users=userService.findByUserId(user.getUserId());
		user.setUserName("update_"+user.getUserName());
		userService.updateUser(user);
	}
	@RequestMapping(value = {"/user"},method={RequestMethod.DELETE})
	@ResponseBody
	public void deleteUser(@RequestParam int userId)
	{
		userService.deleteUser(userId);
	}
	@RequestMapping(value = {"/all"},method={RequestMethod.GET})
	@ResponseBody
	public Object findAll(@RequestParam(defaultValue = "1")int pageNum,
						  @RequestParam(defaultValue = "5")int pageSize) {
		PageInfo users= userService.findAll(pageNum,pageSize);
		return users;
	}
}