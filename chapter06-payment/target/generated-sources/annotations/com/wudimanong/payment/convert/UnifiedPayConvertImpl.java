package com.wudimanong.payment.convert;

import com.wudimanong.payment.dao.model.PayOrderPO;
import com.wudimanong.payment.entity.bo.UnifiedPayBO;
import com.wudimanong.payment.entity.dto.UnifiedPayDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-10T10:58:26+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class UnifiedPayConvertImpl implements UnifiedPayConvert {

    @Override
    public UnifiedPayBO convertUnifiedPayBO(UnifiedPayDTO unifiedPayDTO) {
        if ( unifiedPayDTO == null ) {
            return null;
        }

        UnifiedPayBO unifiedPayBO = new UnifiedPayBO();

        unifiedPayBO.setOrderId( unifiedPayDTO.getOrderId() );
        unifiedPayBO.setAmount( unifiedPayDTO.getAmount() );
        unifiedPayBO.setCurrency( unifiedPayDTO.getCurrency() );
        if ( unifiedPayDTO.getChannel() != null ) {
            unifiedPayBO.setChannel( String.valueOf( unifiedPayDTO.getChannel() ) );
        }

        return unifiedPayBO;
    }

    @Override
    public PayOrderPO convertPayOrderPO(UnifiedPayDTO unifiedPayDTO) {
        if ( unifiedPayDTO == null ) {
            return null;
        }

        PayOrderPO payOrderPO = new PayOrderPO();

        payOrderPO.setOrderId( unifiedPayDTO.getOrderId() );
        payOrderPO.setTradeType( unifiedPayDTO.getTradeType() );
        payOrderPO.setAmount( unifiedPayDTO.getAmount() );
        payOrderPO.setCurrency( unifiedPayDTO.getCurrency() );
        if ( unifiedPayDTO.getChannel() != null ) {
            payOrderPO.setChannel( String.valueOf( unifiedPayDTO.getChannel() ) );
        }
        payOrderPO.setPayType( unifiedPayDTO.getPayType() );
        payOrderPO.setUserId( unifiedPayDTO.getUserId() );

        return payOrderPO;
    }
}
