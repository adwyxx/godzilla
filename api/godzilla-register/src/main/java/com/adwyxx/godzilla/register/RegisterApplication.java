/**
 * @Description: Eureka Reginster Application
 * @Author: Leo Wang
 * @Email: adwyxx@qq.com
 * @Date: 2019-8-24 22:21:06
 */

package com.adwyxx.godzilla.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }
}
