package com.adwyxx.godzilla.oauth.model;

import com.adwyxx.godzilla.oauth.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息Model,包含密码等敏感信息
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 16:24
 */
@Data
@NoArgsConstructor
public class UserModel implements Serializable {
    private static final long serialVersionUID = -3691357242285557626L;
    private Integer id;
    private String loginName;
    private String nickName;
    private String mobile;
    private String email;
    private Boolean isLocked;

    public UserModel(User user){
        this.id = user.getId();
        this.loginName = user.getLoginName();
        this.nickName = user.getNickName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
    }
}
