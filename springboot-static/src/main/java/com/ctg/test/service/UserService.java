package com.ctg.test.service;

import com.ctg.test.model.User;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/25 16:24
 */
public interface UserService {
    public List<User> findByUserName(String userName, String passWord);

    public List<User> findByUserId(int userId);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int userId);

    //批量新增
    public void batchAddUser(List<User> users);

    //批量删除
    public void batchDeleteUser(List<Integer> ids);

    //批量修改1
    public void batchUpdateUser(List<User> users);

    //批量修改2
    public void batchUpdate(List<User> users);

    //批量查询
    public List<User> batchFindUser(List<String> userNames);
}
