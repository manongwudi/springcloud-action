package com.wudimanong.wallet.controller.internal;

import com.wudimanong.wallet.entity.OpenUserAccountResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joe
 */
@RestController
@RequestMapping("/wallet")
@Slf4j
public class UserAccountController {

    @RequestMapping(value = "/openUserAccount", method = RequestMethod.POST)
    public OpenUserAccountResVo openUserAccount(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "accType") String accType,
            @RequestParam(value = "currency") String currency) {
        return null;
    }

    @RequestMapping(value = "/consumeAccount", method = RequestMethod.POST)
    public OpenUserAccountResVo consumeAccount(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "accType") String accType,
            @RequestParam(value = "currency") String currency) {
        return null;
    }

}
