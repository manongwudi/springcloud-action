package com.wudimanong.oauth.config.redis;

import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.entity.model.OauthRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author joe
 * @desc Redis配置类
 */
@Configuration
public class RedisAuthConfiguration {

    @Autowired
    private JedisConnectionFactory con;

    @Bean
    public RedisTemplate<String, OauthRole> baseRoleTemplate() {
        RedisTemplate<String, OauthRole> template = new RedisTemplate();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, OauthModuleResources> baseModelTemplate() {
        RedisTemplate<String, OauthModuleResources> template = new RedisTemplate();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
