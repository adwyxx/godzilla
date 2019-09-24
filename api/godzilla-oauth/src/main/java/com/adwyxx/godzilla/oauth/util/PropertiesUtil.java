package com.adwyxx.godzilla.oauth.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取工具类
 * @author: Leo.Wang,adwyxx@qq.com
 * @date: 2019-09-24 10:43
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    //属性
    private static Properties properties;

    //初始化，加载配置文件
    static {
        //默认的配置文件名称
        String fileName = "redis.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("加载配置文件异常，",e);
        }
    }

    /**
     * 获取配置属性的值
     * @author: Leo.Wang
     * @date: 2019/9/24 11:05
     * @param key: 属性键
     * @return String: 属性值
     */
    public static String getProperty(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value))
        {
            return null;
        }
        return value.trim();
    }

    /**
     * 获取配置属性的值,如果属性没有配置则返回默认值
     * @date: 2019/9/24 11:10
     * @param key: 属性Key
     * @param defaultValue: 默认值
     * @return String: 属性值
     */
    public static String getProperty(String key,String defaultValue){
        String value = getProperty(key);
        if(StringUtils.isBlank(value)){
            return  defaultValue;
        }

        return value;
    }
}
