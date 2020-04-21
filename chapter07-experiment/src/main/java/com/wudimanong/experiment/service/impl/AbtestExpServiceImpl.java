package com.wudimanong.experiment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wudimanong.experiment.client.entity.AbtestExpInfo;
import com.wudimanong.experiment.client.entity.bo.GetExpInfosBO;
import com.wudimanong.experiment.client.entity.dto.GetExpInfosDTO;
import com.wudimanong.experiment.convert.AbtestExpConvert;
import com.wudimanong.experiment.dao.mapper.AbtestExpInfoDao;
import com.wudimanong.experiment.dao.model.AbtestExpInfoPO;
import com.wudimanong.experiment.service.AbtestExpService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
@Slf4j
public class AbtestExpServiceImpl implements AbtestExpService {

    /**
     * 依赖注入实验信息表持久层依赖
     */
    @Autowired
    AbtestExpInfoDao abtestExpInfoDao;

    /**
     * 实验信息列表分页查询逻辑
     *
     * @param getExpInfosDTO
     * @return
     */
    @Override
    public GetExpInfosBO getExpInfos(GetExpInfosDTO getExpInfosDTO) {
        //基于MybatisPlus语法支持拼装查询条件
        QueryWrapper<AbtestExpInfoPO> queryWrapper = new QueryWrapper<>();
        if (getExpInfosDTO.getNameLike() != null && !"".equals(getExpInfosDTO.getNameLike())) {
            queryWrapper.like("name", getExpInfosDTO.getNameLike());
        }
        if (getExpInfosDTO.getFactorTag() != null && !"".equals(getExpInfosDTO.getFactorTag())) {
            queryWrapper.eq("factor_tag", getExpInfosDTO.getFactorTag());
        }
        if (getExpInfosDTO.getStatus() != null) {
            queryWrapper.eq("status", getExpInfosDTO.getStatus());
        }
        //过滤已逻辑删除数据
        queryWrapper.eq("is_delete", 0);
        //进行逆序排序操作
        queryWrapper.orderByDesc("id");
        //MybatisPlus分页支持(设置页码及每页记录数据)
        Page<AbtestExpInfoPO> page = new Page<>(getExpInfosDTO.getPageNo(), getExpInfosDTO.getPageSize());
        //执行分页查询，返回分页查询结果
        IPage<AbtestExpInfoPO> resultPage = abtestExpInfoDao.selectPage(page, queryWrapper);
        //获取具体分页数据
        List<AbtestExpInfoPO> abtestExpInfoPOList = resultPage.getRecords();
        //通过MapStruct数据拷贝插件将持久层列表对象转换为服务层输出对象
        List<AbtestExpInfo> abtestExpInfoList = AbtestExpConvert.INSTANCE.convertAbtestInfo(abtestExpInfoPOList);
        //构造返回输出对象
        GetExpInfosBO getExpInfosBO = GetExpInfosBO.builder().total(Long.valueOf(resultPage.getTotal()).intValue())
                .pageNo(Long.valueOf(resultPage.getCurrent()).intValue()).list(abtestExpInfoList).build();
        return getExpInfosBO;
    }
}
