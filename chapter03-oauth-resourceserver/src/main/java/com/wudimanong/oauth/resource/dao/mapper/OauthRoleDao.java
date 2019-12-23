package com.wudimanong.oauth.resource.dao.mapper;

import com.wudimanong.oauth.entity.model.OauthRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangqiao
 */
@Repository
@Mapper
public interface OauthRoleDao {

    /**
     * 根据用户ID获取用户角色信息
     *
     * @param userId
     * @return
     */
    List<OauthRole> getRoleByUserId(@Param("userId") String userId);
}
