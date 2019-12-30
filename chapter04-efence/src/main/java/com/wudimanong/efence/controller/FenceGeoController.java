package com.wudimanong.efence.controller;

import com.wudimanong.efence.entity.ResponseResult;
import com.wudimanong.efence.entity.bo.BatchImportGeoFenceBO;
import com.wudimanong.efence.entity.dto.BatchImportGeoFenceDTO;
import com.wudimanong.efence.service.FenceGeoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangqiao
 */
@Slf4j
@RestController
@RequestMapping("/fence")
public class FenceGeoController {

    @Autowired
    FenceGeoService fenceGeoServiceImpl;

    /**
     * 批量导入电子围栏数据
     *
     * @param batchImportGeoFenceDTO
     * @return
     */
    @PostMapping("/batchImportGeoFence")
    public ResponseResult<List<BatchImportGeoFenceBO>> batchImportGeoFence(
            @RequestBody @Validated BatchImportGeoFenceDTO batchImportGeoFenceDTO) {
        return ResponseResult.OK(fenceGeoServiceImpl.batchImportGeoFence(batchImportGeoFenceDTO));
    }
}
