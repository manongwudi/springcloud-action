package com.wudimanong.oauth.resource.dao.mapper;

import com.wudimanong.oauth.entity.model.OauthClientDetails;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jiangqiao
 */
@Repository
@Mapper
public interface OauthClientDetailsDao {

    List<OauthClientDetails> selectAll();
}
