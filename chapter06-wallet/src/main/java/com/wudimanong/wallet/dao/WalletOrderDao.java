package com.wudimanong.wallet.dao;

import com.wudimanong.wallet.dao.entity.WalletOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author joe
 */
@Mapper
public interface WalletOrderDao {

    @Select("")
    WalletOrder selectOrderById(String orderId);

    @Insert("")
    int insertOrder(WalletOrder walletOrder);
}
