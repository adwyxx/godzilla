package com.adwyxx.godzilla.oauth.lock;

import lombok.extern.log4j.Log4j;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis分布式锁信息
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-23 17:09
 */
@Component
public class RedissonDistributedLock {
    private static final Logger logger = LoggerFactory.getLogger(RedissonDistributedLock.class);

    @Autowired
    RedissonManager redissonManager;

    public RedissonDistributedLock(){

    }

    /**
     * 获取RLock实例对象
     * @param lockName: 锁名称
     * @return RLock: RLock实例对象
     */
    public RLock getLock(String lockName){
        return redissonManager.getRedisson().getLock(lockName);
    }

    /**
     * 加锁
     * @date: 2019/9/25 17:34
     * @param lockName: 锁名称
     * @return boolean: 加锁是否成功
     */
    public boolean tryLock(String lockName){
        RLock lock = redissonManager.getRedisson().getLock(lockName);
        boolean locked =false;
        locked = lock.tryLock();
        if(locked){
            logger.info("获取分布式锁成功,锁名称:"+lockName);
        }
        else {
            logger.info("获取分布式锁失败,锁名称:"+lockName);
        }
        return locked;
    }
    /**
     * 加锁
     * @param lockName: 锁名称
     * @param expirsTime: 锁的有效期
     * @param unit: 有效期时间单位
     * @return boolean: 加锁是否成功
     */
    public boolean tryLock(String lockName, long expirsTime, TimeUnit unit){
        RLock lock = redissonManager.getRedisson().getLock(lockName);
        boolean locked =false;
        try {
            locked = lock.tryLock(expirsTime,unit);
            if(locked){
               logger.info("获取分布式锁成功,锁名称:"+lockName);
            }
            else {
                logger.info("获取分布式锁失败,锁名称:"+lockName);
            }
        } catch (InterruptedException e) {
            logger.error("获取分布式锁异常,锁名称:"+lockName,e);
        }
        return locked;
    }

    /**
     * 解锁
     * @param lockName: 锁名称
     */
    public void unlock(String lockName){
        redissonManager.getRedisson().getLock(lockName).unlock();
    }
}
