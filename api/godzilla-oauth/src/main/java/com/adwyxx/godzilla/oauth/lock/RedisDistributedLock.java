package com.adwyxx.godzilla.oauth.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.concurrent.TimeUnit;

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

    /**
     * @description: 获取分布式锁
     * @author: Leo.Wang
     * @date: 2019/9/23 23:08
     * @param lock: 锁信息
     * @param timeout: 超时时间
     * @param tryInterval: 尝试获取锁的时间间隔
     * @param lockExpireTime : 锁失效时间
     * @return boolean: 是否成功获取锁
     */
    public boolean getLock(RedisLock lock,long timeout,long tryInterval,long lockExpireTime)
    {
        try{
           if(lock == null || StringUtils.isEmpty(lock.getKey()) || StringUtils.isEmpty(lock.getValue())){
               return false;
            }

            long startTime = System.currentTimeMillis();
            ValueOperations<String,String> opts = null;
            do{
                //锁存在
                if(redisTemplate.hasKey(lock.getKey())){
                    logger.debug("lock is exist!");
                }
                else{
                    //生成锁
                    opts = redisTemplate.opsForValue();
                    opts.set(lock.getKey(),lock.getValue(),lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                }
                //获取锁超时
                if(System.currentTimeMillis()-startTime>timeout){
                    logger.debug("lcok is expired!");
                    return false;
                }

                TimeUnit.MILLISECONDS.sleep(tryInterval);

            } while (redisTemplate.hasKey(lock.getKey()));
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return false;
    }
}
