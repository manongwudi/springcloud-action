package com.wudimanong.oauth.config;

import com.wudimanong.oauth.service.UsernameUserDetailService;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author jiangqiao
 * @说明：配置OAuth2相关内容
 */
@Configuration
@Order(2)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    UsernameUserDetailService usernameUserDetailService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean("jdbcTokenStore")
    public JdbcTokenStore getJdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }


    @Bean("jdbcClientDetailsService")
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置客户端详情
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(getJdbcClientDetailsService());
    }

    /**
     * 用来配置授权（authorization）及令牌（token）的访问端点和令牌服务（token services）
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                // refresh_token需要userDetailsService
                .reuseRefreshTokens(false).userDetailsService(usernameUserDetailService)
                .tokenStore(getJdbcTokenStore());

        //配置TokenService参数
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(endpoints.getTokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setClientDetailsService(endpoints.getClientDetailsService());
        tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
        //30天
        tokenService.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        //50天
        tokenService.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(50));
        tokenService.setReuseRefreshToken(false);
        endpoints.tokenServices(tokenService);

    }

    /**
     * 配置令牌端点（Token Endpoint）的安全约束
     *
     * @param oauthServer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //开启{/oauth/token_key}验证端口无权限访问
        oauthServer.allowFormAuthenticationForClients().tokenKeyAccess("isAuthenticated()")
                // 开启{/oauth/check_token}验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }

    /**
     * 令牌配置管理，使用非对称加密算法来对Token进行签名
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessToken();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "123456".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauthTest"));
        return converter;
    }
}
