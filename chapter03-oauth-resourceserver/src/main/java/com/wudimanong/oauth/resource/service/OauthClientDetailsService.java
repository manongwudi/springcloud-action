package com.wudimanong.oauth.resource.service;

import com.wudimanong.oauth.entity.model.OauthClientDetails;
import java.util.List;

/**
 * @author jiangqiao
 */
public interface OauthClientDetailsService {

    /**
     * 查询所有客户端应用信息
     */
    List<OauthClientDetails> selectAll();
}
