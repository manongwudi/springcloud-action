package com.wudimanong.oauth.config;

import com.wudimanong.oauth.config.filter.LoginAuthenticationFilter;
import com.wudimanong.oauth.config.handler.LoginAuthSuccessHandler;
import com.wudimanong.oauth.config.handler.LogoutHandler;
import com.wudimanong.oauth.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author jiangqiao
 * @说明：编写Spring Security配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernameUserDetailService usernameUserDetailService;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 忽略基本接口的安全过滤
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index", "/confirm_access", "/actuator/health", "/internel/**", "/metrics",
                "/env/**", "/refresh", "/css/**", "/images/**", "/js/**", "/error");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.addFilterAt(getLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").failureUrl("/login?code=").permitAll();
        http.logout().logoutSuccessUrl("/backReferer").permitAll();
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/authorize").permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        http.logout().permitAll()
                .logoutUrl("/logout")
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutSuccessHandler(new LogoutHandler());
    }

    /**
     * 用户验证
     *
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usernameUserDetailService)
                .passwordEncoder(bCryptPasswordEncoder());
        super.configure(auth);
    }

    /**
     * 配置密码加密处理
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider1 = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider1.setUserDetailsService(usernameUserDetailService);
        // 禁止隐藏用户未找到异常
        provider1.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider1.setPasswordEncoder(bCryptPasswordEncoder());
        return provider1;
    }

    /**
     * 用户名密码登录过滤器
     *
     * @return
     */
    @Bean
    public LoginAuthenticationFilter getLoginAuthenticationFilter() {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(new LoginAuthSuccessHandler());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }
}
