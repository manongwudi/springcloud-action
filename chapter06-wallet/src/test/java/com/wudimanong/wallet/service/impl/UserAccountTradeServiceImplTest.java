package com.wudimanong.wallet.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.wudimanong.wallet.client.PaymentClient;
import com.wudimanong.wallet.dao.WalletOrderDao;
import com.wudimanong.wallet.entity.AccountChargeTradeReqVo;
import com.wudimanong.wallet.entity.AccountChargeTradeResVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author joe
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserAccountTradeServiceImpl.class})
//@ActiveProfiles({"test"})
public class UserAccountTradeServiceImplTest {

    @MockBean
    private WalletOrderDao walletOrderDao;

    @MockBean
    private PaymentClient paymentClient;

    @Autowired
    private UserAccountTradeServiceImpl userAccountTradeServiceImpl;

    @Test
    public void accountChargeTradeTest() throws Exception {
        AccountChargeTradeReqVo accountChargeTradeReqVo = AccountChargeTradeReqVo.builder().orderId("12345")
                .userId(1001).amount(1000).currency("CNY").tradeTime("2019070412102023").reNew("1").build();
        AccountChargeTradeResVo accountChargeTradeResVo = userAccountTradeServiceImpl
                .accountChargeTrade(accountChargeTradeReqVo);
        assertNotNull(accountChargeTradeResVo);
        assertEquals(accountChargeTradeResVo.getOrderId(), accountChargeTradeReqVo.getOrderId());
        given(paymentClient.consumeAccount(any(Long.class), any(String.class), any(String.class))).willReturn(null);
        verify(paymentClient).consumeAccount(any(Long.class), any(String.class), any(String.class));
    }
}
