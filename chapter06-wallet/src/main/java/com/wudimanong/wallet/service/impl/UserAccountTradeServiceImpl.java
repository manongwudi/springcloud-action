package com.wudimanong.wallet.service.impl;

import com.wudimanong.wallet.client.PaymentClient;
import com.wudimanong.wallet.dao.WalletOrderDao;
import com.wudimanong.wallet.dao.entity.WalletOrder;
import com.wudimanong.wallet.entity.AccountChargeTradeReqVo;
import com.wudimanong.wallet.entity.AccountChargeTradeResVo;
import com.wudimanong.wallet.service.UserAccountTradeService;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joe
 */
@Service
public class UserAccountTradeServiceImpl implements UserAccountTradeService {

    @Autowired
    WalletOrderDao walletOrderDao;

    @Autowired
    PaymentClient paymentClient;

    @Override
    public AccountChargeTradeResVo accountChargeTrade(AccountChargeTradeReqVo accountChargeTradeReqVo)
            throws Exception {
        //充值交易防重
        WalletOrder walletOrder = walletOrderDao.selectOrderById(accountChargeTradeReqVo.getOrderId());
        if (walletOrder != null) {
            throw new Exception("充值订单重复");
        }
        //构建充值订单
        walletOrder = WalletOrder.builder().orderId(accountChargeTradeReqVo.getOrderId())
                .userId(String.valueOf(accountChargeTradeReqVo.getUserId()))
                .amount(accountChargeTradeReqVo.getAmount())
                .busiType("0").tradeType("charge").currency(accountChargeTradeReqVo.getCurrency()).status("1")
                .isRenew(accountChargeTradeReqVo.getReNew()).tradeTime(new Timestamp(new Date().getTime()))
                .updateTime(new Timestamp(new Date().getTime()))
                .build();
        walletOrderDao.insertOrder(walletOrder);
        //调用支付接口
        paymentClient.consumeAccount(1, "1", "CNY");
        //构建返回参数
        AccountChargeTradeResVo accountChargeTradeResVo = AccountChargeTradeResVo.builder()
                .userId(Long.valueOf(walletOrder.getUserId())).currency(walletOrder.getCurrency())
                .orderId(walletOrder.getOrderId()).businessType(walletOrder.getBusiType()).build();
        return accountChargeTradeResVo;
    }
}
