package com.adwyxx.godzilla.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @Author: Leo Wang
 * @Email: adwyxx@qq.com
 * @Date: $2019-08-24 22:42
 **/
@SpringBootApplication
@EnableEurekaClient
public class OAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
