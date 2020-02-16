package com.wudimanong.wallet.service.impl;

import com.wudimanong.wallet.client.PaymentClient;
import com.wudimanong.wallet.client.bo.UnifiedPayBO;
import com.wudimanong.wallet.client.dto.UnifiedPayDTO;
import com.wudimanong.wallet.convert.UserBalanceOrderConvert;
import com.wudimanong.wallet.dao.mapper.UserBalanceOrderDao;
import com.wudimanong.wallet.dao.model.UserBalanceOrderPO;
import com.wudimanong.wallet.entity.BusinessCodeEnum;
import com.wudimanong.wallet.entity.GlobalCodeEnum;
import com.wudimanong.wallet.entity.ResponseResult;
import com.wudimanong.wallet.entity.bo.AccountChargeBO;
import com.wudimanong.wallet.entity.dto.AccountChargeDTO;
import com.wudimanong.wallet.entity.enums.TradeType;
import com.wudimanong.wallet.exception.DAOException;
import com.wudimanong.wallet.exception.ServiceException;
import com.wudimanong.wallet.service.UserAccountTradeService;
import com.wudimanong.wallet.utils.DateUtils;
import com.wudimanong.wallet.utils.IDutils;
import com.wudimanong.wallet.utils.SnowFlakeIdGenerator;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
public class UserAccountTradeServiceImpl implements UserAccountTradeService {


    @Autowired
    PaymentClient paymentClient;

    /**
     * 充值订单Dao层接口
     */
    @Autowired
    UserBalanceOrderDao userBalanceOrderDao;

    @Override
    public AccountChargeBO chargeOrder(AccountChargeDTO accountChargeDTO) {
        //生成充值订单信息
        UserBalanceOrderPO userBalanceOrderPO = createChargeOrder(accountChargeDTO);
        try {
            userBalanceOrderDao.insert(userBalanceOrderPO);
        } catch (Exception e) {
            //抛出Dao层处理失败异常
            throw new DAOException(BusinessCodeEnum.BUSI_CHARGE_FAIL_2000.getCode(),
                    BusinessCodeEnum.BUSI_CHARGE_FAIL_2000.getDesc(), e);
        }
        //***********调用支付微服务支付接口****************
        //构建支付请求参数
        UnifiedPayDTO unifiedPayDTO = buildUnifiedPayDTO(accountChargeDTO, userBalanceOrderPO);
        ResponseResult<UnifiedPayBO> responseResult = paymentClient.unifiedPay(unifiedPayDTO);
        if (!responseResult.getCode().equals(GlobalCodeEnum.GL_SUCC_0000.getCode())) {
            //支付失败返回错误码
            throw new ServiceException(responseResult.getCode(), responseResult.getMessage());
        }
        //得到支付返回参数
        UnifiedPayBO unifiedPayBO = responseResult.getData();
        //返回生成的充值订单信息
        AccountChargeBO accountChargeBO = UserBalanceOrderConvert.INSTANCE
                .convertUserBalanceOrderBO(unifiedPayBO);
        accountChargeBO.setUserId(accountChargeDTO.getUserId());
        return accountChargeBO;
    }

    /**
     * 生成充值订单信息私有方法
     *
     * @param accountChargeDTO
     * @return
     */
    private UserBalanceOrderPO createChargeOrder(AccountChargeDTO accountChargeDTO) {
        UserBalanceOrderPO userBalanceOrderPO = UserBalanceOrderConvert.INSTANCE
                .convertUserBalanceOrderPO(accountChargeDTO);
        //生成充值订单流水号
        String orderId = getOrderId();
        userBalanceOrderPO.setOrderId(orderId);
        //设置交易类型为充值
        userBalanceOrderPO.setTradeType(TradeType.CHARGE.getCode());
        //设置支付状态为待支付
        userBalanceOrderPO.setStatus("0");
        //设置交易时间
        userBalanceOrderPO.setTradeTime(new Timestamp(System.currentTimeMillis()));
        //设置订单创建时间
        userBalanceOrderPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //设置订单初始更新时间
        userBalanceOrderPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return userBalanceOrderPO;
    }

    /**
     * 以特定规则生成用户充值订单流水号私有方法
     *
     * @return
     */
    private String getOrderId() {
        //雪花算法ID生成器
        SnowFlakeIdGenerator idGenerator = new SnowFlakeIdGenerator(IDutils.getWorkId(), 1);
        //以日期yyyyMMddHHmmss+随机ID生成器的方式生成充值订单号
        return DateUtils.getStringByFormat(new Date(), DateUtils.sf3) + idGenerator.nextId();
    }

    /**
     * 构建请求支付微服务的统一支付请求参数对象
     *
     * @param userBalanceOrderPO
     * @return
     */
    private UnifiedPayDTO buildUnifiedPayDTO(AccountChargeDTO accountChargeDTO, UserBalanceOrderPO userBalanceOrderPO) {
        UnifiedPayDTO unifiedPayDTO = new UnifiedPayDTO();
        //支付微服务为接入方分配的应用ID
        unifiedPayDTO.setAppId("10001");
        //支付业务订单号
        unifiedPayDTO.setOrderId(userBalanceOrderPO.getOrderId());
        //充值交易类型-余额充值
        unifiedPayDTO.setTradeType("topup");
        //支付渠道
        unifiedPayDTO.setChannel(accountChargeDTO.getPaymentType());
        //具体的支付渠道方式，可根据接入的支付产品设定
        unifiedPayDTO.setPayType("ALI_PAY_H5");
        //支付金额
        unifiedPayDTO.setAmount(accountChargeDTO.getAmount());
        //支付币种
        unifiedPayDTO.setCurrency(accountChargeDTO.getCurrency());
        //商户用户标识
        unifiedPayDTO.setUserId(String.valueOf(accountChargeDTO.getUserId()));
        //商品标题，实际情况下根据所购买的商品定义相关内容
        unifiedPayDTO.setSubject("xiaomi 10 pro");
        //商品详情
        unifiedPayDTO.setBody("xiaomi 10 pro testing");
        //支付结果异步通知地址，根据实际情况填充，这里为假的测试地址
        unifiedPayDTO.setNotifyUrl("http://www.baidu.com");
        //支付结果同步返回地址，一般为用户前端页面，根据实际情况填充，这里为假的测试地址
        unifiedPayDTO.setReturnUrl("http://www.baidu.com");
        return unifiedPayDTO;
    }
}
