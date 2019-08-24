/**
 * @Description: Gateway Application manin class
 * @Author: Leo Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-8-24 22:21:06
 */

package com.adwyxx.godzilla.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
