package com.ctg.test.durid.service.impl;


import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * @Description: mock测试service方法增删查改
 * https://blog.csdn.net/qq_30141957/article/details/81273829
 * @Author: yanhonghai
 * @Date: 2019/3/12 11:32
 */

public class UserServiceImplTest {
    @Mock
    UserService userService;
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
    public void findByUserName() {
        User user=new User();
        user.setId(10);
        user.setUserName("user10");
        user.setPassWord("123456");
        //测试输入和期望的输出：当使用 user10 查询数据库的时候，返回user
        when(userService.findByUserName("user10")).thenReturn(user);
        //测试调用
        User user1=userService.findByUserName("user10");
        assertNotNull(user1);
        assertEquals(10, user1.getId().intValue());
        //验证是否执行过1次
        verify(userService, times(1)).findByUserName(eq("user10"));

    }

    @Test
    public void findAll() {

        List<User> users=new ArrayList<>();
        when(userService.findAll()).thenReturn(users);
    }

    @Test
    public void addUser() {
        User user=new User();
        user.setId(10);
        user.setUserName("user10");
        user.setPassWord("123456");
        //测试
        userService.addUser(user);
        //抓取器
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        //验证是否执行过1次
        verify(userService, times(1)).addUser(userCaptor.capture());
        // 获取抓取到的参数值
        User user1 = userCaptor.getValue();
        assertNotNull(user1);
        assertEquals("user10", user1.getUserName());
    }

    @Test
    public void updateUser() {
        User user=new User();
        user.setId(10);
        user.setUserName("user10_update2");
        user.setPassWord("123456");
        //测试
        userService.updateUser(user);
        //抓取器
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        //验证是否执行过1次
        verify(userService, times(1)).updateUser(userCaptor.capture());
        // 获取抓取到的参数值
        User user1 = userCaptor.getValue();
        assertNotNull(user1);
        assertEquals("user10_update2", user1.getUserName());

    }
    @Test
    public void deleteUser() {
        //测试
        userService.deleteUser(10);
        //验证是否执行过1次
        verify(userService, times(1)).deleteUser(10);
        //测试调用
        User user=userService.findByUserName("user10");
        assertNull(user);
        //方法没有返回值时可以用上面的验证是否执行过；或者下面的
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                return "called with arguments: " + args;
            }
        }).when(userService).findAll();
    }
}
