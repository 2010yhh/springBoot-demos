package com.ctg.test.cros.springbootcros.controller;


import com.ctg.test.cros.springbootcros.util.DBUtil;
import com.ctg.test.cros.springbootcros.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试sql,xss
 */
@Controller
@EnableAutoConfiguration
public class TestController {
	@Autowired
	private Environment env;
	@Autowired
	IpUtil ipUtil;

	/**
	 * http://localhost:8180/webapp2/testXss?userName=1&passWord=<script>alert(1)</script>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/testXss")
	@ResponseBody
	String testXss(@RequestParam String userName, @RequestParam String passWord, HttpServletRequest request, HttpServletResponse response) {

		return "testXss";
	}

	/**
	 * 测试错误的sql：http://localhost:8180/webapp2/testSql1?userName=1&passWord=1 or 1=1
	 * @param userName
	 * @param passWord
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = {"/testSql1"})
	@ResponseBody
	public Object testSql1(@RequestParam String userName, @RequestParam String passWord,
                           HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Map<String,Object> result=new HashMap<>();
		//slq注入 输入 or 1=1
		String sql1 ="select user_name,pass_word from cas_user where user_name= '"+userName+"' and pass_word="+passWord;
	    System.out.print("sql1:"+sql1+"\n");
		Statement st=DBUtil.getConnection().createStatement();
		//4.处理数据库的返回结果(使用ResultSet类)
		ResultSet rs=st.executeQuery(sql1);
		int i=0;
		while(rs.next()){
			i=i+1;
			result.put("user_name"+i,rs.getString("user_name"));
			result.put("pass_word",rs.getString("pass_word"));
		}
		//关闭资源
		rs.close();
		st.close();
		return result;
	}

	/**
	 * 测试正确的操作sql：http://localhost:8180/webapp2/testSql2?userName=1&passWord=1 or 1=1
	 * @param userName
	 * @param passWord
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = {"/testSql2"})
	@ResponseBody
	public Object testSql2(@RequestParam String userName, @RequestParam String passWord,
                           HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Map<String,Object> result=new HashMap<>();
			//正确也可以利用PreparedStatement预编译，再设置参数
		String sql2 ="select user_name,pass_word from cas_user where user_name= '"+userName+"' and pass_word= '"+passWord+"'";
		System.out.print("sql2:"+sql2+"\n");
		Statement st=DBUtil.getConnection().createStatement();
		//正确也可以利用PreparedStatement预编译，再设置参数
		/*PreparedStatement pst=DBUtil.getConnection().prepareStatement(sql2);
		pst.setString(1,userName);
		pst.setString(2,passWord);*/
		//4.处理数据库的返回结果(使用ResultSet类)
		ResultSet rs=st.executeQuery(sql2);
		int i=0;
		while(rs.next()){
			i=i+1;
			result.put("user_name"+i,rs.getString("user_name"));
			result.put("pass_word",rs.getString("pass_word"));
		}
		//关闭资源
		rs.close();
		st.close();
		return result;
	}
}