package com.wudimanong.payment.convert;

import com.wudimanong.payment.dao.model.PayNotifyPO;
import com.wudimanong.payment.dao.model.PayOrderPO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-10T10:58:25+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class PayNotifyConvertImpl implements PayNotifyConvert {

    @Override
    public PayNotifyPO convertPayNotifyPO(PayOrderPO payOrderPO) {
        if ( payOrderPO == null ) {
            return null;
        }

        PayNotifyPO payNotifyPO = new PayNotifyPO();

        payNotifyPO.setId( payOrderPO.getId() );
        payNotifyPO.setPayId( payOrderPO.getPayId() );
        if ( payOrderPO.getChannel() != null ) {
            payNotifyPO.setChannel( Integer.parseInt( payOrderPO.getChannel() ) );
        }
        payNotifyPO.setStatus( payOrderPO.getStatus() );
        payNotifyPO.setOrderId( payOrderPO.getOrderId() );
        payNotifyPO.setUpdateTime( payOrderPO.getUpdateTime() );
        payNotifyPO.setCreateTime( payOrderPO.getCreateTime() );

        return payNotifyPO;
    }
}
