package com.adwyxx.godzilla.oauth.lock;

/**
 * Redis 服务器部署类型：单体服务器或者集群模式
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-24 15:19
 */
public enum RedisConnectionType {
    STANDALONE("standalone", "单节点部署方式"),
    SENTINEL("sentinel", "哨兵部署方式"),
    CLUSTER("cluster", "集群方式"),
    MASTERSLAVE("masterslave", "主从部署方式");

    private final String type;
    private final String description;

    private RedisConnectionType(String type,String description){
        this.type=type;
        this.description = description;
    }

    public String getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean eques(RedisConnectionType type){
        return this.type == type.getType();
    }

    public boolean eques(String type){
        return this.type == type;
    }
}
