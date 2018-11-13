package com.ctg.test.mapper;

import com.ctg.test.model.User;
import com.ctg.test.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //批量新增
    public void batchAddUser(List<User>users);

    //批量删除
    public void batchDeleteUser(List<Integer>ids);
    //批量修改1
    public void batchUpdateUser(List<User>users);
    //批量修改2
    public void  batchUpdate(List<User>users);

    //批量查询
    public List<User> batchFindUser(List<String>userNames);

}