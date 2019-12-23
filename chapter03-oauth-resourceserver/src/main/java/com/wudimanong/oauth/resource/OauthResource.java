package com.wudimanong.oauth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author jiangqiao
 */
@EnableDiscoveryClient
@SpringBootApplication
//添加资源服务器注解
@EnableResourceServer
public class OauthResource {

    public static void main(String[] args) {
        SpringApplication.run(OauthResource.class, args);
    }
}
