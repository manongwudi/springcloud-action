package com.wudimanong.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author joe
 */
@EnableDiscoveryClient
@SpringBootApplication
@SessionAttributes("authorizationRequest")
public class AuthServer {

    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class, args);
    }
}
