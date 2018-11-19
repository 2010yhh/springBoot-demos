package com.ctg.test.service;

import com.ctg.test.model.User;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/19 10:03
 */
public interface UserService {
    public User findByUserName(String userName);
    public User findUserInfo(String userName);
    public List<User> findAll();
}
