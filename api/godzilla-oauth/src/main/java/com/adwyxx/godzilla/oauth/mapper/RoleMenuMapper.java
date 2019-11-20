package com.adwyxx.godzilla.oauth.mapper;

import com.adwyxx.godzilla.oauth.entity.RoleMenuKey;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(RoleMenuKey key);

    int insert(RoleMenuKey record);

    int insertSelective(RoleMenuKey record);
}