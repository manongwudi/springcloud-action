package com.wudimanong.resourceserver.entity.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @author jiangqiao
 */
@Data
@Builder
public class CheckPassWordBO {

    /**
     * 认证结果
     */
    private Boolean result;

}
