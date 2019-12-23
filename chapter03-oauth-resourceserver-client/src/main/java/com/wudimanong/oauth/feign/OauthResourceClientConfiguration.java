package com.wudimanong.oauth.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangqiao
 */
@Slf4j
@Configuration
public class OauthResourceClientConfiguration {

    @Bean
    OauthResourceClientFallbackFactory oauthResourceClientFallbackFactory() {
        return new OauthResourceClientFallbackFactory();
    }
}
