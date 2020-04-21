package com.wudimanong.experiment.client.entity.dto;

import lombok.Data;

/**
 * @author jiangqiao
 */
@Data
public class GetExpInfosDTO {

    /**
     * 实验名称模糊匹配
     */
    private String nameLike;

    /**
     * 根据业务标签精准匹配
     */
    private String factorTag;

    /**
     * 状态（0 新建, 1 已发布, 2 生效中, 3 已暂停, 4 已终止, 5 已结束）
     */
    private Integer status;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;
}
