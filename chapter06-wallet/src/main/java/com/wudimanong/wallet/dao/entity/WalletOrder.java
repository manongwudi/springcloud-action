package com.wudimanong.wallet.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

/**
 * @author joe
 */
@Data
@Builder
public class WalletOrder implements Serializable {

    private String orderId;
    private String userId;
    private int amount;
    private String busiType;
    private String tradeType;
    private String currency;
    private String tradeNo;
    private String status;
    private String isRenew;
    private Timestamp tradeTime;
    private Timestamp updateTime;
}
