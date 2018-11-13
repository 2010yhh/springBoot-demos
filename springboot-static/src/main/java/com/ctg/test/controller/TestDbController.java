package com.ctg.test.controller;

import com.ctg.test.model.User;
import com.ctg.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *http://localhost:8090/login?userName=yhh&passWord=yhh
 */
@Controller
public class TestDbController {
	@Autowired
	UserService userService;
	/**
	 * 测试增删改,ecache缓存，db回滚
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
	/**
	 * 测试：批量增删查改测试，db回滚
	 */
	@RequestMapping(value = {"/batchUser"},method={ RequestMethod.GET})
	@ResponseBody
	public Object batchFindUser() {
		Map<String,Object> result=new HashMap<>();
		List<String>userNames=new ArrayList<>();
		for(int i=0;i<3;i++){
			userNames.add("user00"+i);
		}
		List<User>  users=userService.batchFindUser(userNames);
		result.put("code","200");
		result.put("msg","success");
		result.put("user",users);
		return result;
	}
	@RequestMapping(value = {"/batchUser"},method={RequestMethod.POST})
	@ResponseBody
	public Object batchAddUser() {
		List<User>users=new ArrayList<>();
		for(int i=0;i<3;i++){
			User user=new User();
			user.setUserName("user00"+i);
			user.setPassWord("123456");
			user.setRealName("user00"+i+"_RealName");
			users.add(user);
		}
		userService.batchAddUser(users);
		Map<String,Object> result=new HashMap<>();
		result.put("code","200");
		result.put("msg","success");
		return result;
	}
	@RequestMapping(value = {"/batchUser"},method={RequestMethod.PUT})
	@ResponseBody
	public void batchUpdateUser() {
		List<String>userNames=new ArrayList<>();
		for(int i=0;i<3;i++){
			userNames.add("user00"+i);
		}
		List<User>  users=userService.batchFindUser(userNames);
		for(int i=0;i<users.size();i++){
			//users.get(i).setUserName("batchUpdateUseruser00"+i);

			users.get(i).setUserName("batchUpdateUser_user00"+i);
			users.get(i).setRealName("batchUpdateUser_user00"+i+"_RealName");

		}
		userService.batchUpdateUser(users);
	}
	@RequestMapping(value = {"/batchUser2"},method={RequestMethod.PUT})
	@ResponseBody
	public void batchUpdate() {
		List<String>userNames=new ArrayList<>();
		for(int i=0;i<3;i++){
			userNames.add("user00"+i);
		}
		List<User>  users=userService.batchFindUser(userNames);
		for(int i=0;i<users.size();i++){
			//users.get(i).setUserName("batchUpdateuser00"+i);
			users.get(i).setUserName("batchUpdate_user00"+i);
			users.get(i).setRealName("batchUpdate_user00"+i+"_RealName");
		}
		userService.batchUpdate(users);
	}
	@RequestMapping(value = {"/batchUser"},method={RequestMethod.DELETE})
	@ResponseBody
	public void batchDeleteUser() {
		List<Integer>ids=new ArrayList<>();
		List<String>userNames=new ArrayList<>();
		for(int i=0;i<3;i++){
			userNames.add("user00"+i);
		}
		List<User>  users=userService.batchFindUser(userNames);
		for(int i=0;i<users.size();i++){
			ids.add(users.get(i).getUserId());
		}
		userService.batchDeleteUser(ids);
	}

}