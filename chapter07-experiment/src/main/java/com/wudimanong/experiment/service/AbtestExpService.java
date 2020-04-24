package com.wudimanong.experiment.service;

import com.wudimanong.experiment.client.entity.bo.CreateExpBO;
import com.wudimanong.experiment.client.entity.bo.GetExpInfosBO;
import com.wudimanong.experiment.client.entity.dto.CreateExpDTO;
import com.wudimanong.experiment.client.entity.dto.GetExpInfosDTO;

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
    GetExpInfosBO getExpInfos(GetExpInfosDTO getExpInfosDTO);

    /**
     * 创建实验
     *
     * @param createExpDTO
     * @return
     */
    CreateExpBO createExp(CreateExpDTO createExpDTO);
}
