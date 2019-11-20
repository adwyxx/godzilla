package com.adwyxx.godzilla.oauth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Token信息检查结果
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 16:21
 */
@Data
@NoArgsConstructor
public class TokenCheckResult implements Serializable {
    private static final long serialVersionUID = -920113144659473519L;

    private boolean success;
    private UserModel user;
    private JwtErrorType errorType;
    private String message;

}
