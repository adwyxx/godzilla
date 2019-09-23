package com.adwyxx.godzilla.oauth.lock;

/**
 * @Description: Redis分布式锁信息
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-23 17:09
 */
public class RedisLock {
    private String key;
    private String value;

    public RedisLock(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
