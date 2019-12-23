package com.wudimanong.wallet.service;

import com.wudimanong.wallet.entity.AccountChargeTradeReqVo;
import com.wudimanong.wallet.entity.AccountChargeTradeResVo;

/**
 * @author joe
 */
public interface UserAccountTradeService {

    AccountChargeTradeResVo accountChargeTrade(AccountChargeTradeReqVo accountChargeTradeReqVo) throws Exception;

}
