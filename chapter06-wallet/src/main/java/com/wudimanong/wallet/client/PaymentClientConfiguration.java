package com.wudimanong.wallet.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjinhao on 2017/9/06.
 */
@Configuration
public class PaymentClientConfiguration {

    @Bean
    PaymentClientFallback paymentClientFallback() {
        return new PaymentClientFallback();
    }
}
