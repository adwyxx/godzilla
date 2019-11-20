package com.adwyxx.godzilla.oauth.service;

import com.adwyxx.godzilla.oauth.entity.User;

import java.util.Date;

/**
 * 用户服务接口
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 11:03
 */
public interface UserService {
    //根据用户名和密码获取User
    User getByPass(String loginName,String password);
    //验证用户名是否存在
    boolean isExistsUserName(String userName);
    //根据Id获取User
    User getByUserId(int id);
    //保存用户信息
    int save(User user);
    //更新用户基本信息（不包含用户名，密码更新）
    void update(User user);
    //禁用用户
    boolean disabled(int id);
    //锁定用户
    boolean lock(int id, Date begin,Date end);
}
