package com.adwyxx.godzilla.oauth;

import com.adwyxx.godzilla.common.lock.RedissonManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
* RedissonManager Tester.
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={OAuthApplication.class,RedissonManagerTest.class})
@ComponentScan(value = { "com.adwyxx.godzilla.oauth", "com.adwyxx.godzilla.common"})
public class RedissonManagerTest { 

    @Autowired
    RedissonManager redissonManager;

    /**
    *
    * Method: getRedisson()
    *
    */
    @Test
    public void testGetRedisson() throws Exception {
        redissonManager.getRedisson();
    }

    /**
    *
    * Method: createConfig(String connectionType, String address)
    *
    */
    @Test
    public void testCreateConfig() throws Exception {

    }

    /**
    *
    * Method: createConfigByYaml(String yamlFileName)
    *
    */
    @Test
    public void testCreateConfigByYaml() throws Exception {

    }
} 
