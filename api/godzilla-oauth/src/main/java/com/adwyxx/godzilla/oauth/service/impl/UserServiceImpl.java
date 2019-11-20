package com.adwyxx.godzilla.oauth.service.impl;

import com.adwyxx.godzilla.oauth.entity.User;
import com.adwyxx.godzilla.oauth.mapper.UserMapper;
import com.adwyxx.godzilla.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务接口实现 {@link UserService}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 10:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getByPass(String loginName, String password) {
        return userMapper.selectByPass(loginName,password);
    }

    @Override
    public boolean isExistsUserName(String userName) {
        return userMapper.checkByLoginName(userName)>0;
    }

    @Override
    public User getByUserId(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean disabled(int id) {
        return false;
    }

    @Override
    public boolean lock(int id, Date begin, Date end) {
        return false;
    }
}
