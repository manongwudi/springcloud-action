package com.wudimanong.payment.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * @author joe
 */
@Data
@Builder
public class OpenUserAccountResVo implements Serializable {

    private String result;

}
