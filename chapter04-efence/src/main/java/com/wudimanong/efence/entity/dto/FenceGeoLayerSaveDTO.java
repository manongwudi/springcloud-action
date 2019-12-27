package com.wudimanong.efence.entity.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author jiangqiao
 */
@Data
public class FenceGeoLayerSaveDTO implements Serializable {

    /**
     * 图层编码
     */
    private String code;

    /**
     * 图层名称
     */
    private String name;

    /**
     * 图层业务类型：0-未知分类；1-干预；2-调度；4-运营范围；5-技术
     */
    private Integer businessType;

    private Integer cityCode;
    /**
     * 地理范围：0-全球；1-国内；2-海外
     */
    private Integer regionType;

    /**
     * 详细说明
     */
    private String explain;

    /**
     * 数据负责人，格式为"名字+邮箱"
     */
    private String owner;
}
