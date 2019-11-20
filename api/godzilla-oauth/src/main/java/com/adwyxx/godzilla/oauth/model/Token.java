package com.adwyxx.godzilla.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Token
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/20 9:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String clientId;
    private int userId;
    private String userName;
    private Date exprise;
}
