package com.wudimanong.wallet.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * @author joe
 */
@Data
@Builder
public class AccountChargeTradeReqVo implements Serializable {

    private String orderId;
    private long userId;
    private int amount;
    private String currency;
    private String tradeTime;
    private String reNew;
}
