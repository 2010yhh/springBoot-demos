package com.ctg.test.springboottkmybatis.service.impl;


import com.ctg.test.springboottkmybatis.mapper.UserMapper;
import com.ctg.test.springboottkmybatis.model.User;
import com.ctg.test.springboottkmybatis.model.UserExample;
import com.ctg.test.springboottkmybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@CacheConfig(cacheNames = {"user"})
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Cacheable(key = "#userName")
    @Override
    public List<User> findByUserName(String userName, String passWord) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andPassWordEqualTo(passWord);
        return userMapper.selectByExample(example);
    }

    @Cacheable(key = "#userId")
    @Override
    public List<User> findByUserId(int userId) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userMapper.selectByExample(example);
    }

    /**
     * 测试pageHelper分页查询
     * @return
     */
    @Override
    public PageInfo  findAll(int pageNum,int pageSize) {
        //当第三个参数没有或者为true的时候，进行count查询
        //应避免多线程不按照，判断参数或者按照下面的释放线程中的ThreadLocal变量
        ////紧跟着的第一个select方法会被分页;后面的不会被分页，除非再次调用PageHelper.startPage
        List<User> users;
        try {
            PageHelper.startPage(pageNum, pageSize);
            users = userMapper.selectByExample(new UserExample());
        } finally {
            PageHelper.clearPage();
        }
        //Page<User>page=(Page<User>)users;
        PageInfo pageInfo = new PageInfo(users);
       return pageInfo;
       // return page;
    }

    @CachePut(key = "#user.userId")
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addUser(User user) {
        //测试事物特性
        userMapper.insertSelective(user);
         //userMapper.insertSelective(user);
    }

    @CachePut(key = "#user.userId")
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @CacheEvict(key = "#id")
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(int userId) {
        //测试事物特性
        //userMapper.insertSelective(user);
        userMapper.deleteByPrimaryKey(userId);
    }

}
