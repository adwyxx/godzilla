package com.adwyxx.godzilla.oauth.service.impl;

import com.adwyxx.godzilla.oauth.entity.User;
import com.adwyxx.godzilla.oauth.model.TokenCheckResult;
import com.adwyxx.godzilla.oauth.model.UserModel;
import com.adwyxx.godzilla.oauth.service.AccessTokenService;
import com.adwyxx.godzilla.oauth.service.UserService;
import com.adwyxx.godzilla.oauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link AccessTokenService}接口 实现类
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 16:45
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    UserService userService;

    @Override
    public String generateToke(String clientId, int userId) {
        User user = userService.getByUserId(userId);
        return JwtUtil.generateToken(clientId,new UserModel(user));
    }

    @Override
    public String generateToke(String clientId, UserModel user) {
        return JwtUtil.generateToken(clientId,user);
    }

    @Override
    public TokenCheckResult chekToken(String token) {
        return JwtUtil.validateToken(token);
    }

    @Override
    public String refreshToken(String token) {
        return null;
    }
}
