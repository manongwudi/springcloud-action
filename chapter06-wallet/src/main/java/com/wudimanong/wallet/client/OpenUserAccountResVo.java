package com.wudimanong.wallet.client;

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
