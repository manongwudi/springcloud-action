package com.wudimanong.wallet;

import com.wudimanong.wallet.client.PaymentClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jiangqiao
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.wudimanong.wallet.dao.mapper")
@EnableFeignClients(basePackageClasses = PaymentClient.class)
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }
}
