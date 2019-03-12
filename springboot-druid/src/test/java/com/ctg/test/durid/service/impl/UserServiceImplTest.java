package com.ctg.test.durid.service.impl;


import com.alibaba.fastjson.JSON;
import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: junit单元测试service方法
 * @Author: yanhonghai
 * @Date: 2019/3/12 11:32
 */
//Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
//获取启动类，加载配置,寻找主配置启动类（被@SpringBootApplication 注解的）
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void findByUserName() {
        System.out.print(JSON.toJSONString(userService.findByUserName("user1")));
    }

    @Test
    public void findAll() {
        System.out.print(JSON.toJSONString(userService.findAll()));
    }

    @Test
    public void addUser() {
        String user="{\"id\":6,\"userName\":\"yhh_addUser_6\",\"passWord\":\"2222\"}";
        userService.addUser(JSON.parseObject(user,User.class));
    }

    @Test
    public void updateUser() {
        String user="{\"id\":6,\"userName\":\"yhh_addUser_6_update\",\"passWord\":\"333\"}";
        userService.updateUser(JSON.parseObject(user,User.class));
    }
    @Test
    public void deleteUser() {
        userService.deleteUser(7);
    }
}
