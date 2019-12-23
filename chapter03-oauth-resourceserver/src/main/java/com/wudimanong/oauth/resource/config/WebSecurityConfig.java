package com.wudimanong.oauth.resource.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jiangqiao
 * @说明：编写Spring Security配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 忽略基本接口的安全过滤
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/health", "/internel/**", "/metrics", "/env/**", "/refresh",
                "/css/**", "/static/**", "/images/**", "/js/**");
    }
}
