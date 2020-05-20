package com.wudimanong.resourceserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jiangqiao
 */
@EnableWebSecurity
public class ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 配置受访问保护的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/**").authenticated();
        http.csrf().disable();
    }
}
