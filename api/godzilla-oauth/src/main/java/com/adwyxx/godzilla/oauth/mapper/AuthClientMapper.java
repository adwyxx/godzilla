package com.adwyxx.godzilla.oauth.mapper;

import com.adwyxx.godzilla.oauth.entity.AuthClient;

public interface AuthClientMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(AuthClient record);

    int insertSelective(AuthClient record);

    AuthClient selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(AuthClient record);

    int updateByPrimaryKey(AuthClient record);
}