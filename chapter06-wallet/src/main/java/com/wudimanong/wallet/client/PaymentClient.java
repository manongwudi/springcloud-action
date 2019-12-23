package com.wudimanong.wallet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author joe
 */
@FeignClient(value = "payment", configuration = PaymentClientConfiguration.class, fallback = PaymentClientFallback.class)
public interface PaymentClient {

    // 支付扣款方法
    @RequestMapping(value = "/pay/doPay", method = RequestMethod.POST)
    public OpenUserAccountResVo consumeAccount(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "accType") String accType,
            @RequestParam(value = "currency") String currency);

}
