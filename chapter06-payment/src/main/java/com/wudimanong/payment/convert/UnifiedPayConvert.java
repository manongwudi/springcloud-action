package com.wudimanong.payment.convert;

import com.wudimanong.payment.entity.bo.UnifiedPayBO;
import com.wudimanong.payment.entity.dto.UnifiedPayDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author jiangqiao
 */
@org.mapstruct.Mapper
public interface UnifiedPayConvert {

    UnifiedPayConvert INSTANCE = Mappers.getMapper(UnifiedPayConvert.class);

    /**
     * 充值订单数据生成转换方法
     *
     * @param unifiedPayDTO
     * @return
     */
    @Mappings({
            @Mapping(target = "extraInfo", ignore = true)
    })
    UnifiedPayBO convertUnifiedPayBO(UnifiedPayDTO unifiedPayDTO);

}
