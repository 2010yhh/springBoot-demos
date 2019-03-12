package com.ctg.test.durid.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @Description: mockMvc测试web的http接口
 * @Author: yanhonghai
 * @Date: 2019/3/12 11:26
 */
//Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
//获取启动类，加载配置,寻找主配置启动类（被@SpringBootApplication 注解的）
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    //private MockHttpSession session;
    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        //测试需要登录的接口
        //session = new MockHttpSession();
        //User user =new User("root","root");
       // session.setAttribute("user",user);
    }
    @Test
    public void addUser()throws Exception {
        String user="{\"id\":5,\"userName\":\"yhh_addUser\",\"passWord\":\"111111\"}";
        mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(user.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUser() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/user?id=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getUser()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user?userName=yhh_addUser_updateUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateUser() throws Exception{
        String user="{\"id\":5,\"userName\":\"yhh_addUser_updateUser\",\"passWord\":\"22222\"}";
        mvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(user.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
