package com.ctg.test.mapper;

import com.ctg.test.model.UserRole;
import com.ctg.test.model.UserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}