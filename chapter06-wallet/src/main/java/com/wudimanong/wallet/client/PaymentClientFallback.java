package com.wudimanong.wallet.client;

/**
 * @author joe
 */
public class PaymentClientFallback implements PaymentClient {

    @Override
    public OpenUserAccountResVo consumeAccount(long userId, String accType, String currency) {
        System.out.println("被熔断了");
        return null;
    }
}

