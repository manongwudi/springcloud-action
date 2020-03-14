package com.wudimanong.experiment.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author jiangqiao
 */
//启用缓存
@EnableCaching
@Configuration
public class CacheConfig {

    /**
     * 缓存默认大小
     */
    public static final int DEFAULT_MAXSIZE = 50000;
    /**
     * 缓存默认过期时间
     */
    public static final int DEFAULT_TTL = 10;

    /**
     * 定义多种cache名称、超时时长（秒）、最大容量;需要修改可以在构造方法的参数中指定。
     */
    public enum Caches {
        //实验信息，缓存有效期5秒
        EXP_INFO(5),
        //分层信息,缓存有效期30分钟
        LAYER_INFO(1800, 1000),
        ;

        /**
         * 最大數量
         */
        private int maxSize = DEFAULT_MAXSIZE;
        /**
         * 过期时间（秒）
         */
        private int ttl = DEFAULT_TTL;

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public int getTtl() {
            return ttl;
        }
    }

    /**
     * 创建基于Caffeine的Cache Manager
     *
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //设置多种不同的缓存策略
        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            //在最后一次写入缓存后开始计时，在指定的时间后过期
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            //缓存最大容量大小
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
