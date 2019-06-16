package com.ctg.test.springboottkmybatis.service;


import com.ctg.test.springboottkmybatis.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/25 16:24
 */
public interface UserService {
    public List<User> findByUserName(String userName, String passWord);

    public List<User> findByUserId(int userId);
    public PageInfo findAll(int pageNum, int pageSize);
    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int userId);

}
