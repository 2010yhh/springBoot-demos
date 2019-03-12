package com.ctg.test.durid.service;





import com.ctg.test.durid.entity.User;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/19 10:03
 */
public interface UserService {
    public User findByUserName(String userName);
    public List<User> findAll();
    public void addUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
}
