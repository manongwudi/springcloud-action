package com.wudimanong.efence.service.impl;

import com.wudimanong.efence.entity.bo.BatchImportGeoFenceBO;
import com.wudimanong.efence.entity.dto.BatchImportGeoFenceDTO;
import com.wudimanong.efence.service.FenceGeoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Slf4j
@Service
public class FenceGeoServiceImpl implements FenceGeoService {

    /**
     * 批量导入电子围栏业务层方法实现
     *
     * @param batchImportGeoFenceDTO
     * @return
     */
    @Override
    public List<BatchImportGeoFenceBO> batchImportGeoFence(BatchImportGeoFenceDTO batchImportGeoFenceDTO) {
        return null;
    }
}
