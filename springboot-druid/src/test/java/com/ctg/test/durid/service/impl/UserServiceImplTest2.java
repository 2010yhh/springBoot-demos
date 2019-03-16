package com.ctg.test.durid.service.impl;


import com.ctg.test.durid.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;


/**
 * @Description:
 * 1、Mock声明的对象，对函数的调用均执行mock（即虚假函数），不执行真正部分。
 * 2、Spy声明的对象，对函数的调用均执行真正部分。
 * https://blog.csdn.net/qq_30141957/article/details/81273829
 * @Author: yanhonghai
 * @Date: 2019/3/12 11:32
 */

public class UserServiceImplTest2 {

    /**
     * 在需要Mock的属性上标记@Mock注解，然后@RunWith(MockitoJUnitRunner.class)
     * 或者在setUp()方法中显示调用MockitoAnnotations.initMocks(this);生成Mock类即可。
     */
    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        //或者：UserService userService=  Mockito.mock(UserService.class);
        // UserService userService2=  Mockito.spy(UserService.class);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findByUserNameSpy() {
        //spy下执行实际方法
        UserServiceImpl userService2=  Mockito.spy(UserServiceImpl.class);
        userService2.findByUserName("user10");
      }
    @Test
    public void findByUserNameMock() {
        UserServiceImpl userService=  Mockito.mock(UserServiceImpl.class);
        //mock 下thenCallRealMethod执行实际方法
        when(userService.findByUserName(anyString())).thenCallRealMethod();
        userService.findByUserName("user10");
        //正常情况mock不执行实际方法，无输出
        User user=new User();
        user.setId(10);
        user.setUserName("user10");
        user.setPassWord("123456");
        //测试输入和期望的输出：当使用 user10 查询数据库的时候，返回user
        when(userService.findByUserName("user10")).thenReturn(user);
        //测试调用
        User user1=userService.findByUserName("user10");
    }
}
