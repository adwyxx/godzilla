package com.adwyxx.godzilla.common.test;

import com.adwyxx.godzilla.common.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link RedisUtil} 单元测试
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/12/4 17:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisUtilTest.class)
@ComponentScan("com.adwyxx.godzilla.common")
public class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testSet(){
        redisUtil.set("111",111);
    }

    @Test
    public void testGet(){
        Object value = redisUtil.get("111");
        System.out.println(value);
    }
}
