package com.wudimanong.wallet.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * @author joe
 */
@Data
@Builder
public class AccountChargeTradeResVo implements Serializable {

    private long userId;
    private int amount;
    private String currency;
    private String orderId;
    private String businessType;
}
