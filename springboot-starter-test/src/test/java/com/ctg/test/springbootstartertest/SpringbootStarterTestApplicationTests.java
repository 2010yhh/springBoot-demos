package com.ctg.test.springbootstartertest;

import com.ctg.test.springbootuserstarter.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStarterTestApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
    }


    @Test
    public void starterTest() {
        userService.print();
    }
}
