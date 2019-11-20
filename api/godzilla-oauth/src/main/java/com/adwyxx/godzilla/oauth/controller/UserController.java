package com.adwyxx.godzilla.oauth.controller;

import com.adwyxx.godzilla.oauth.entity.User;
import com.adwyxx.godzilla.oauth.model.UserModel;
import com.adwyxx.godzilla.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关API
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 9:44
 */
@RestController("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getuser")
    public UserModel getUser(String loginName,String password){
        User user =userService.getByPass(loginName,password);
        if(user!=null)
        {
            return new UserModel(user);
        }
        return null;
    }
}

