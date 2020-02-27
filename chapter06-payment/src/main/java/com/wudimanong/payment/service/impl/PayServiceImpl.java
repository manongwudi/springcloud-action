package com.wudimanong.payment.service.impl;

import com.wudimanong.payment.dao.mapper.PayOrderDao;
import com.wudimanong.payment.entity.bo.UnifiedPayBO;
import com.wudimanong.payment.entity.dto.UnifiedPayDTO;
import com.wudimanong.payment.service.PayChannelService;
import com.wudimanong.payment.service.PayChannelServiceFactory;
import com.wudimanong.payment.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    /**
     * 支付订单持久层接口依赖
     */
    @Autowired
    PayOrderDao payOrderDao;

    @Autowired
    PayChannelServiceFactory payChannelServiceFactory;

    @Override
    public UnifiedPayBO unifiedPay(UnifiedPayDTO unifiedPayDTO) {
        //todo 支付订单防重判断
        //todo 支付订单入库

        //获取具体的支付渠道服务
        PayChannelService payChannelService = payChannelServiceFactory
                .createPayChannelService(unifiedPayDTO.getChannel());
        //调用渠道支付方法
        UnifiedPayBO unifiedPayBO = payChannelService.pay(unifiedPayDTO);
        return unifiedPayBO;
    }
}
