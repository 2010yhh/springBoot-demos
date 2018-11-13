package com.ctg.test.service.impl;

import com.ctg.test.mapper.UserMapper;
import com.ctg.test.model.User;
import com.ctg.test.model.UserExample;
import com.ctg.test.service.UserService;
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

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/25 16:21
 * <p>
 * 声明式事务管理： 建立在AOP之上的。其本质是对方法前后进行拦截，
 * 然后在目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。
 * 声明式事务管理不需要入侵代码，通过@Transactional就可以进行事务操作，更快捷而且简单
 * <p>
 * 隔离级别
 * 隔离级别是指若干个并发的事务之间的隔离程度，与我们开发时候主要相关的场景包括：脏读取、重复读、幻读
 */

/**
 * 传播行为
 * 所谓事务的传播行为是指，如果在开始当前事务之前，
 * 一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为
 */
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

    @Override
    @Transactional
    public void batchAddUser(List<User> users) {
        userMapper.batchAddUser(users);
    }

    @Override
    @Transactional
    public void batchDeleteUser(List<Integer> ids) {
        userMapper.batchDeleteUser(ids);
    }

    @Override
    @Transactional
    public void batchUpdateUser(List<User> users) {
        userMapper.batchUpdateUser(users);
    }

    @Override
    @Transactional
    public void batchUpdate(List<User> users) {
        userMapper.batchUpdate(users);
    }

    @Override
    @Transactional
    public List<User> batchFindUser(List<String> userNames) {
        return userMapper.batchFindUser(userNames);
    }
}
