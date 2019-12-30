package com.wudimanong.efence.service;

import com.wudimanong.efence.entity.bo.BatchImportGeoFenceBO;
import com.wudimanong.efence.entity.dto.BatchImportGeoFenceDTO;
import java.util.List;

/**
 * @author jiangqiao
 */
public interface FenceGeoService {

    /**
     * 批量导入电子围栏业务层方法
     *
     * @param batchImportGeoFenceDTO
     * @return
     */
    List<BatchImportGeoFenceBO> batchImportGeoFence(BatchImportGeoFenceDTO batchImportGeoFenceDTO);
}
