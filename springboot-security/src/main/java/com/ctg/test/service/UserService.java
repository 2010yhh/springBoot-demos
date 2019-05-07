package com.ctg.test.service;

import com.ctg.test.model.User;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/19 10:03
 */
public interface UserService {

    User findByUserName(String userName);

    User findUserInfo(String userName);

    List<User> findAll();
}
