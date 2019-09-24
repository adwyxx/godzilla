package com.adwyxx.godzilla.oauth.lock;

import com.adwyxx.godzilla.oauth.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Redisson Manager
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-24 10:34
 */
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    private static Config config = null;
    private static Redisson redisson = null;

    //初始化Redisson配置
    static {
        String address = PropertiesUtil.getProperty("redisson.lock.server.address");
        String connectionType = PropertiesUtil.getProperty("redisson.lock.server.type","standalone");
        config = RedissonConfigFactory.getInstance().createConfig(connectionType,address);

        //创建Redisson实例
        redisson = (Redisson)Redisson.create(config);
        logger.info("初始化Redisson：",config);
    }

    public static Redisson getRedisson(){
        return redisson;
    }

    //Redisson连接配置工厂，单例模式
    static class RedissonConfigFactory{
        //静态实例对象
        private static volatile RedissonConfigFactory factory = null;
        //私有构造方法
        private RedissonConfigFactory(){}

        /**
         * 获取实例对象
         * @date: 2019/9/24 15:58
         * @return RedissonConfigFactory: 配置工厂实例对象
         */
        private static RedissonConfigFactory getInstance(){
            //DCL,双重检查锁，保证线程安全
            if(factory == null){
                synchronized (RedissonConfigFactory.class){
                    if(factory==null){
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        Config createConfig(String connectionType,String address){
            if(StringUtils.isBlank(connectionType) || StringUtils.isBlank(address)){
                logger.info("Redisson配置异常：connectionType:"+connectionType+",address:"+address);
                return null;
            }
            Config config = new Config();
            //单机模式
            if(connectionType.equals(RedisConnectionType.STANDALONE.getType())){
                config.useSingleServer().setAddress(address);
            }
            else {
                String[] arrAddress = address.split(",");
                if(address.length()<2){
                    config.useSingleServer().setAddress(address);
                }
                else
                {
                    //主从模式
                    if(connectionType.equals(RedisConnectionType.MASTERSLAVE.getType())){
                        MasterSlaveServersConfig conf = config.useMasterSlaveServers();
                        conf.setMasterAddress(arrAddress[0]);
                        for (int i = 1; i < arrAddress.length; i++) {
                            conf.addSlaveAddress(arrAddress[i]);
                        }
                    }
                    //哨兵模式
                    if(connectionType.equals(RedisConnectionType.SENTINEL.getType())){
                        SentinelServersConfig conf = config.useSentinelServers();
                        conf.setMasterName(arrAddress[0]);
                        for (int i = 1; i < arrAddress.length; i++) {
                            conf.addSentinelAddress(arrAddress[i]);
                        }
                    }
                    //集群模式
                    if(connectionType.equals(RedisConnectionType.CLUSTER.getType())) {
                        config.useClusterServers().addNodeAddress(arrAddress);
                    }
                }
            }

            return config;
        }
    }
}
