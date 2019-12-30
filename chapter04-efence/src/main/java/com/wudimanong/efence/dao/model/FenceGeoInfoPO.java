package com.wudimanong.efence.dao.model;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author jiangqiao
 */
@Data
@TableName("fence_geo_info")
@KeySequence(value = "fence_geo_id_seq")
public class FenceGeoInfoPO implements Serializable {

}
