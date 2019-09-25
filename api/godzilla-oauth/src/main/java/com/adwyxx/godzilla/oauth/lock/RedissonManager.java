package com.adwyxx.godzilla.oauth.lock;

import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: Redisson Manager
 * @Author: Leo.Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-09-24 10:34
 */
@Component
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    private static Config config = null;
    private static Redisson redisson = null;

    //初始化Redisson配置
    static {
        //String address = PropertiesUtil.getProperty("redisson.com.adwyxx.godzilla.oauth.lock.server.address");
        //String connectionType = PropertiesUtil.getProperty("redisson.com.adwyxx.godzilla.oauth.lock.server.type","standalone");
        //config = RedissonConfigFactory.getInstance().createConfig(connectionType,address);
        config = RedissonConfigFactory.getInstance().createConfigByYaml("redisson-config.yml");
        //创建Redisson实例
        redisson = (Redisson)Redisson.create(config);
        logger.info("初始化Redisson：",config);
    }

    public Redisson getRedisson(){
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

        /**
         * 根据连接类型和服务器地址（列表）生成配置信息
         * @param connectionType: 连接类型，支持：standalone,masterslave,sentinel,cluster
         * @param address: 服务器地址，多个用英文逗号分隔，例如：192.168.1.101:6379,192.168.1.101:6379
         * @return Config: 配置信息
         */
        Config createConfig(String connectionType,String address){
            if(StringUtils.isBlank(connectionType) || StringUtils.isBlank(address)){
                logger.info("Redisson配置异常：connectionType:"+connectionType+",address:"+address);
                return null;
            }
            Config config = new Config();
            //单机模式
            String type = connectionType.toLowerCase().trim();
            if(type.equals(RedisConnectionType.STANDALONE.getType())){
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
                    if(type.equals(RedisConnectionType.MASTERSLAVE.getType())){
                        MasterSlaveServersConfig conf = config.useMasterSlaveServers();
                        conf.setMasterAddress(arrAddress[0]);
                        for (int i = 1; i < arrAddress.length; i++) {
                            conf.addSlaveAddress(arrAddress[i]);
                        }
                    }
                    //哨兵模式
                    if(type.equals(RedisConnectionType.SENTINEL.getType())){
                        SentinelServersConfig conf = config.useSentinelServers();
                        conf.setMasterName(arrAddress[0]);
                        for (int i = 1; i < arrAddress.length; i++) {
                            conf.addSentinelAddress(arrAddress[i]);
                        }
                    }
                    //集群模式
                    if(type.equals(RedisConnectionType.CLUSTER.getType())) {
                        config.useClusterServers().addNodeAddress(arrAddress);
                    }
                }
            }

            return config;
        }

        /**
         * 根据yaml配置文件生成配置信息
         * @param yamlFileName: yaml配置文件名称
         * @return Config: 配置信息
         */
        Config createConfigByYaml(String yamlFileName){
            if(StringUtils.isBlank(yamlFileName)){
                logger.info("Redisson配置异常：无法找到配置文件:"+yamlFileName);
                return null;
            }
            Config config = null;
            try {
                config = Config.fromYAML(RedissonManager.class.getClassLoader().getResource(yamlFileName));
                //config = Config.fromYAML(new InputStreamReader(RedissonManager.class.getClassLoader().getResourceAsStream(yamlFileName),"UTF-8"));
            } catch (IOException e) {
                logger.error("加载Redisson配置文件异常：",e);
            }
            return config;
        }
    }
}
