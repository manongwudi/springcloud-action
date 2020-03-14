package com.wudimanong.experiment.service;

import com.wudimanong.experiment.dao.model.AbtestExpInfoPO;

/**
 * @author jiangqiao
 */
public interface AbtestExpInfoService {

    /**
     * 根据业务标签获取实验配置信息
     *
     * @param factorTag
     * @return
     */
    AbtestExpInfoPO getExpInfoByFactorTag(String factorTag);

}
