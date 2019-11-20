package com.adwyxx.godzilla.oauth.mapper;

import com.adwyxx.godzilla.oauth.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据用户名密码获取用户信息
    User selectByPass(@Param(value = "loginName") String loginName, @Param(value = "password") String password);
    //验证用户名是否存在
     int checkByLoginName(String loginName);
}