package com.adwyxx.godzilla.oauth.model;

/**
 * JWT token验证错误类型枚举
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 16:35
 */
public enum JwtErrorType {
    EXPIRE("Token已过期"),
    ILLEGAL_SIGNATURE("数字签名无效"),
    FAIL("无效Token");

    //消息
    private String message;
    //构造函数
    private JwtErrorType(String msg){
        this.message = msg;
    }
}
