package com.wudimanong.wallet.controller.external;

import com.wudimanong.wallet.entity.APIResponse;
import com.wudimanong.wallet.entity.AccountChargeTradeReqVo;
import com.wudimanong.wallet.entity.AccountChargeTradeResVo;
import com.wudimanong.wallet.service.UserAccountTradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserAccountTradeController {

    @Autowired
    UserAccountTradeService userAccountTradeServiceImpl;

    @RequestMapping(value = "/accountChargeTrade", method = RequestMethod.POST)
    public APIResponse accountChargeTrade(@RequestParam(value = "userId") long userId,
            @RequestParam(value = "currency") String currency, @RequestParam(value = "amount") int amount,
            @RequestParam(value = "tradeTime", required = false) String tradeTime,
            @RequestParam(value = "renew", required = false) String renew) {

        AccountChargeTradeReqVo accountChargeTradeReqVo = AccountChargeTradeReqVo.builder().userId(userId)
                .currency(currency).tradeTime(tradeTime).reNew(renew).build();
        AccountChargeTradeResVo accountChargeTradeResVo;
        try {
            accountChargeTradeResVo = userAccountTradeServiceImpl.accountChargeTrade(accountChargeTradeReqVo);
        } catch (Exception e) {
            log.error(e.toString() + "_" + e, e);
            return APIResponse.error(-1, e.getMessage());
        }
        return APIResponse.success(0, "SUCCESS", accountChargeTradeResVo);
    }
}
