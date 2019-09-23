package com.adwyxx.godzilla.oauth.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: Redis分布式锁实现
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-23 17:09
 * 参考：https://blog.csdn.net/forezp/article/details/68957681
 * https://www.jianshu.com/p/f612339d3171
 */
@Component
public class RedisDistributedLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
    private final static long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间30s，防止死锁
    private final static long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock()
    {

        return false;
    }

    public boolean tryLock(){
        return false;
    }

    public void unlock(){


    }
}
