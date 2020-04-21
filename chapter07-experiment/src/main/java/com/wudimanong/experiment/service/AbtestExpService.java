package com.wudimanong.experiment.service;

import com.wudimanong.experiment.client.entity.bo.GetExpInfosBO;
import com.wudimanong.experiment.client.entity.dto.GetExpInfosDTO;
import org.springframework.validation.annotation.Validated;

/**
 * @author jiangqiao
 */
public interface AbtestExpService {

    /**
     * 分页查询实验信息列表
     *
     * @param getExpInfosDTO
     * @return
     */
    GetExpInfosBO getExpInfos(@Validated GetExpInfosDTO getExpInfosDTO);

}
