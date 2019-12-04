package com.adwyxx.godzilla.common.util;

/**
 * Redis 操作的Key前缀信息定义
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/12/4 16:23
 */
public interface RedisPrefixKey {
   String prefix=null;
   long timeout=0;
   long expire=0;

   //获取Key的前缀
   default String getPrefix(){
       return prefix;
   }
   //获取保存缓存操作等待时间（秒）
   default long getTimeout(){
       return timeout;
   }
   //设置缓存过期时长（秒）
   default long getExpire(){
       return expire;
   }
}
