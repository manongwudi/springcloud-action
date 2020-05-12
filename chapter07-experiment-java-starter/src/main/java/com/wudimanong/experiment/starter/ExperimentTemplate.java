package com.wudimanong.experiment.starter;

import com.wudimanong.experiment.client.entity.AbtestExp;
import com.wudimanong.experiment.client.entity.AbtestGroup;
import com.wudimanong.experiment.client.entity.bo.ConfigBO;
import com.wudimanong.experiment.starter.entity.MatchResult;
import com.wudimanong.experiment.starter.entity.MatchResult.RetrieveType;
import com.wudimanong.experiment.starter.feign.ExperimentFeignSource;
import com.wudimanong.experiment.starter.utils.AbtestUtils;
import java.util.Base64;
import java.util.Optional;

/**
 * @author jiangqiao
 */
public class ExperimentTemplate {

    /**
     * 设置实验Spring Cloud微服务Feign接口组件依赖
     */
    private ExperimentFeignSource experimentFeignSource;

    public ExperimentTemplate(ExperimentFeignSource experimentFeignSource) {
        this.experimentFeignSource = experimentFeignSource;
    }

    /**
     * 根据指定ID获取分流结果
     *
     * @param factorTag
     * @param currIdStr
     * @return
     */
    public MatchResult get(String factorTag, String currIdStr) {
        return math(factorTag, currIdStr);
    }

    /**
     * 实验业务标签+分流ID匹配方法
     *
     * @param factorTag
     * @param currIdStr
     */
    private MatchResult math(String factorTag, String currIdStr) {
        //以Feign的方式获取微服务实验配置信息
        ConfigBO result = experimentFeignSource.getDeliverConfig(factorTag);
        if (result == null) {
            //实验配置获取失败,返回空配置
            return MatchResult.builder().build();
        }
        //获取返回配置信息中的实验配置信息
        AbtestExp abtestExp = result.getAbtestExp();
        //计算当前层流程分桶编号（关键逻辑）
        Long currBucketNo = AbtestUtils.getBucketNo(currIdStr, abtestExp.getLayerId());
        //匹配实验配置信息中流量分组信息
        Optional<AbtestGroup> abtestGroup0 = abtestExp.getAbtestGroups().stream()
                .filter(lt -> lt.getIsUseBase64Nums() ? (
                        new String(Base64.getUrlDecoder().decode(lt.getPartitionSerialNums64()))
                                .indexOf(currBucketNo.toString()) > 0
                                ? true : false) : lt.getPartitionSerialNums().contains(currBucketNo)).findFirst();
        AbtestGroup destGroup = abtestExp.getAbtestGroups().stream()
                .filter(lt -> lt.getPartitionSerialNums().contains(currBucketNo))
                .findFirst().get();
        return MatchResult.builder().destGroup(destGroup).abtestExp(abtestExp).retrieveType(RetrieveType.BUCKET)
                .build();
    }
}
