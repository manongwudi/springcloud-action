package com.wudimanong.efence;

import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author jiangqiao
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.wudimanong.efence.dao.mapper")
public class FenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenceApplication.class, args);
    }

    /**
     * MyBatis-Plus Postgresql序列Key生成器配置
     *
     * @return
     */
    @Bean
    public PostgreKeyGenerator createPostgreKeyGenerator() {
        return new com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator();
    }
}
