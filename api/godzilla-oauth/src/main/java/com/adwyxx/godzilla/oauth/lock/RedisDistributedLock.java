package com.adwyxx.godzilla.oauth.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis分布式锁实现
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-23 17:09
 * 应用：
 * @Autowired
 * RedisDistributedLock com.adwyxx.godzilla.oauth.lock;
 * if(com.adwyxx.godzilla.oauth.lock.tryLock("com.adwyxx.godzilla.oauth.lock key","com.adwyxx.godzilla.oauth.lock value"){
 * 	do something ...
 * 	com.adwyxx.godzilla.oauth.lock.unlock("com.adwyxx.godzilla.oauth.lock");
 * }
 */
@Component
public class RedisDistributedLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
    private final static long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间30s，防止死锁
    private final static long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @description: 尝试获取锁
     * @author: Leo.Wang
     * @date: 2019/9/24 9:50
     * @param key: 锁名称
     * @param value: 锁对应的值
     * @return boolean: 是否获取到锁
     */
    public boolean tryLock(String key,String value){
        return getLock(key,value,LOCK_TRY_TIMEOUT,LOCK_TRY_INTERVAL,LOCK_EXPIRE);
    }

    /**
     * @description: 尝试获取锁
     * @author: Leo.Wang
     * @date: 2019/9/24 9:50
     * @param key: 锁名称
     * @param value: 锁对应的值
     * @param timeout:获取锁超时时间，单位：微妙
     * @return boolean: 是否获取到锁
     */
    public boolean tryLock(String key,String value,long timeout)
    {
        return getLock(key,value,timeout,LOCK_TRY_INTERVAL,LOCK_EXPIRE);
    }

    /**
     * @description: 尝试获取锁
     * @author: Leo.Wang
     * @date: 2019/9/24 9:50
     * @param key: 锁名称
     * @param value: 锁对应的值
     * @param timeout:获取锁超时时间，单位：微妙
     * @param tryInterval:多少ms尝试一次，单位：微妙
     * @return boolean: 是否获取到锁
     */
    public boolean tryLock(String key,String value,long timeout,long tryInterval){
        return getLock(key,value,timeout,tryInterval,LOCK_EXPIRE);
    }

    /**
     * @description: 尝试获取锁
     * @author: Leo.Wang
     * @date: 2019/9/24 9:50
     * @param key: 锁名称
     * @param value: 锁对应的值
     * @param timeout:获取锁超时时间，单位：微妙
     * @param tryInterval:多少ms尝试一次，单位：微妙
     * @param expires:锁超时时间，单位：微妙
     * @return boolean: 是否获取到锁
     */
    public boolean tryLock(String key,String value,long timeout,long tryInterval,long expires){
        return getLock(key,value,timeout,LOCK_TRY_INTERVAL,expires);
    }

    /**
     * @description: 释放锁
     * @author: Leo.Wang
     * @date: 2019/9/24 9:48
     * @param key: 锁名称
     */
    public void unlock(String key){
        if(!StringUtils.isEmpty(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * @description: 获取分布式锁
     * @author: Leo.Wang
     * @date: 2019/9/23 23:08
     * @param key: 键
     * @param value: 键值
     * @param timeout: 超时时间
     * @param tryInterval: 尝试获取锁的时间间隔
     * @param lockExpireTime : 锁失效时间
     * @return boolean: 是否成功获取锁
     */
    public boolean getLock(String key,String value,long timeout,long tryInterval,long lockExpireTime)
    {
        try{
           if(StringUtils.isEmpty(key) || StringUtils.isEmpty(value)){
               return false;
            }

            long startTime = System.currentTimeMillis();
            ValueOperations<String,String> opts = null;
            do{
                //锁存在
                if(redisTemplate.hasKey(key)){
                    logger.debug("com.adwyxx.godzilla.oauth.lock is exist!");
                }
                else{
                    //生成锁
                    opts = redisTemplate.opsForValue();
                    opts.set(key,value,lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                }
                //获取锁超时
                if(System.currentTimeMillis()-startTime>timeout){
                    logger.debug("com.adwyxx.godzilla.oauth.lock is expired!");
                    return false;
                }

                TimeUnit.MILLISECONDS.sleep(tryInterval);

            } while (redisTemplate.hasKey(key));
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return false;
    }
}
