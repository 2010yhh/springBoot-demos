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
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/25 16:21
 */
/**
 * redis做缓存
 * @Cacheable：在方法执行前Spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；没有则调用方法并将方法返回值放进缓存
 * @CachePut：将方法的返回值放到缓存中
 *
 */
@Service
@CacheConfig(cacheNames = {"user"})
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    //@Cacheable(key ="#p0")//指定传入的第一个参数作为redis的key
    @Cacheable(key = "#userName")
    @Override
    public User findByUserName(String userName, String passWord) {
        UserExample example=new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andPassWordEqualTo(passWord);

        List<User>users=userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
    }

    @CachePut(key = "#user.userName")
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class )
    public User addUser(User user) {
        if(userMapper.insertSelective(user)!=0){
            return user;
        }
        return null;
    }

    @CachePut(key = "#user.userName")
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class )
    public User updateUser(User user) {

        if(userMapper.updateByPrimaryKeySelective(user)!=0){
            return user;
        }
        return null;
    }

    //拼接key
    @CacheEvict(key = "'user_'+#userId",allEntries=true)
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class )
    public void deleteUser(int userId) {
        userMapper.deleteByPrimaryKey(userId);
    }
}
