package com.wudimanong.wallet.service;

import com.wudimanong.wallet.entity.bo.AccountChargeBO;
import com.wudimanong.wallet.entity.dto.AccountChargeDTO;

/**
 * @author jiangqiao
 */
public interface UserAccountTradeService {

    /**
     * 创建余额充值订单业务层接口方法
     *
     * @param accountChargeDTO
     * @return
     */
    AccountChargeBO chargeOrder(AccountChargeDTO accountChargeDTO);
}
