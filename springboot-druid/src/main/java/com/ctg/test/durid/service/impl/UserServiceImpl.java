package com.ctg.test.durid.service.impl;



import com.ctg.test.durid.entity.User;
import com.ctg.test.durid.entity.UserExample;
import com.ctg.test.durid.mapper.UserMapper;

import com.ctg.test.durid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
        System.out.print(System.currentTimeMillis()+":execute findByUserName,userName:"+userName);
        UserExample example=new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<User> users=new ArrayList<>();
        users=userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return new User();
    }
    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
