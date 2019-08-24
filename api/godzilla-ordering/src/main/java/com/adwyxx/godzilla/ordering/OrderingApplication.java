package com.adwyxx.godzilla.ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: Ordering Service Application
 * @Author: Leo Wang
 * @Email: adwyxx@qq.com
 * @Date: $2019-08-24 22:31
 **/
@SpringBootApplication
@EnableEurekaClient
public class OrderingApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderingApplication.class, args);
    }
}
