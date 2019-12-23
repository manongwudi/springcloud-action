package com.wudimanong.oauth.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author joe
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * ftl视图
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/confirm_access").setViewName("authorize");
        registry.addViewController("/").setViewName("index");
    }
}
