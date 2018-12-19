package com.ctg.test.durid.service.impl;



import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.entity.UserExample;
import com.ctg.test.durid.mapper.UserMapper;

import com.ctg.test.durid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/17 0:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findByUserName(String userName) {
        UserExample example=new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<User> users=userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
    }
    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(new UserExample());
    }
}
