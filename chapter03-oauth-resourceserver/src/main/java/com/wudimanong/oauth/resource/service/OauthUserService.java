package com.wudimanong.oauth.resource.service;

import com.wudimanong.oauth.entity.model.OauthUser;

/**
 * @author jiangqiao
 */
public interface OauthUserService {

    /**
     * 根据条件实例查询一个对象
     */
    OauthUser selectOneByUserName(String userName);
}
