package com.adwyxx.godzilla.oauth.mapper;

import com.adwyxx.godzilla.oauth.entity.UserRoleKey;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);
}