package com.adwyxx.godzilla.oauth.service;

import com.adwyxx.godzilla.oauth.entity.AuthClient;

/**
 * OAuth认证客户端信息接口
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 11:04
 */
public interface AuthClientService {
    void save(AuthClient client);
    void update(AuthClient client);
    void disable(String clientId);
    AuthClient getById(String clientId);
}
