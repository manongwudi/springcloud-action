package com.wudimanong.wallet.service.impl;

import com.wudimanong.wallet.convert.UserBalanceOrderConvert;
import com.wudimanong.wallet.dao.mapper.UserBalanceOrderDao;
import com.wudimanong.wallet.dao.model.UserBalanceOrderPO;
import com.wudimanong.wallet.entity.BusinessCodeEnum;
import com.wudimanong.wallet.entity.bo.AccountChargeBO;
import com.wudimanong.wallet.entity.dto.AccountChargeDTO;
import com.wudimanong.wallet.entity.enums.TradeType;
import com.wudimanong.wallet.exception.DAOException;
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
        //调用"第6章支付微服务支付服务接口"

        //返回生成的充值订单信息
        AccountChargeBO accountChargeBO = UserBalanceOrderConvert.INSTANCE
                .convertUserBalanceOrderBO(userBalanceOrderPO);
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
}
