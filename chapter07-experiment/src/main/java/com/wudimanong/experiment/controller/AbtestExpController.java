package com.wudimanong.experiment.controller;

import com.wudimanong.experiment.client.entity.ResponseResult;
import com.wudimanong.experiment.client.entity.bo.GetExpInfosBO;
import com.wudimanong.experiment.client.entity.dto.GetExpInfosDTO;
import com.wudimanong.experiment.service.AbtestExpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 * @desc 实验配置接口
 */
@Slf4j
@RestController
@RequestMapping("/expInfo")
public class AbtestExpController {

    /**
     * 依赖注入Service层接口
     */
    @Autowired
    AbtestExpService abtestExpServiceImpl;

    /**
     * 分页查询实验信息列表
     *
     * @return
     */
    @GetMapping("/getExpInfos")
    public ResponseResult<GetExpInfosBO> getExpInfos(@Validated GetExpInfosDTO getExpInfosDTO) {
        return ResponseResult.OK(abtestExpServiceImpl.getExpInfos(getExpInfosDTO));
    }
}
