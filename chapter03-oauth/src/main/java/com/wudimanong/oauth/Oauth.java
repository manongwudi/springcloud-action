package com.wudimanong.oauth;

import com.wudimanong.oauth.feign.OauthResourceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author joe
 */
@EnableFeignClients(basePackageClasses = {OauthResourceClient.class})
@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer //启动类添加
@SessionAttributes("authorizationRequest")
public class Oauth {

    public static void main(String[] args) {
        SpringApplication.run(Oauth.class, args);
    }
}
