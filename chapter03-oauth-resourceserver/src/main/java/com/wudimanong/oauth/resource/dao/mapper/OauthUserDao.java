package com.wudimanong.oauth.resource.dao.mapper;

import com.wudimanong.oauth.entity.model.OauthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiangqiao
 */
@Repository
@Mapper
public interface OauthUserDao {

    OauthUser selectOneByUserName(@Param("userName") String userName);
}
