package com.wudimanong.payment.controller.internal;

import com.wudimanong.payment.entity.OpenUserAccountResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joe
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @RequestMapping(value = "/doPay", method = RequestMethod.POST)
    public OpenUserAccountResVo consumeAccount(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "accType") String accType,
            @RequestParam(value = "currency") String currency) {
        System.out.println("被调用了");
        return OpenUserAccountResVo.builder().result("success").build();
    }
}
