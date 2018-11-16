package com.ctg.test.service;

import com.ctg.test.model.User;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/25 16:24
 */
public interface UserService {
      User findByUserName(String userName, String passWord);
      User addUser(User user);
      User updateUser(User user);
      void deleteUser(int userId);
}
