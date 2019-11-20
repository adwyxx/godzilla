package com.adwyxx.godzilla.oauth.service;

import com.adwyxx.godzilla.oauth.model.TokenCheckResult;
import com.adwyxx.godzilla.oauth.model.UserModel;

/**
 * Access Token 服务接口
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 11:03
 */
public interface AccessTokenService {
    String generateToke(String clientId, int userId);
    String generateToke(String clientId, UserModel user);
    TokenCheckResult chekToken(String token);
    String refreshToken(String token);
}
