package com.wudimanong.experiment.controller;

import com.wudimanong.experiment.client.entity.ResponseResult;
import com.wudimanong.experiment.dao.model.AbtestExpInfoPO;
import com.wudimanong.experiment.service.AbtestExpInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 * @desc 用于向SDK同步实验配置信息的接口
 */
@Slf4j
@RestController
@RequestMapping("/config")
public class AbtestDeliverController {

    /**
     * 实验配置业务层依赖接口
     */
    @Autowired
    AbtestExpInfoService abtestExpInfoServiceImpl;

    /**
     * 根据实验业务标签获取实验信息
     *
     * @param factorTag
     */
    @GetMapping("/findByFactorTag")
    public ResponseResult<AbtestExpInfoPO> findByFactorTag(@RequestParam("factorTag") String factorTag) {
        AbtestExpInfoPO abtestExpInfoPO = abtestExpInfoServiceImpl.getExpInfoByFactorTag(factorTag);
        return ResponseResult.OK(abtestExpInfoPO);
    }
}
