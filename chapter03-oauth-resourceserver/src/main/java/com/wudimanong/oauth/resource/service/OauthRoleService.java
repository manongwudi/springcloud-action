package com.wudimanong.oauth.resource.service;

import com.wudimanong.oauth.entity.model.OauthRole;
import java.util.List;

/**
 * @author jiangqiao
 */
public interface OauthRoleService {

    /**
     * 根据用户查询角色
     *
     * @param userId
     * @return
     */
    List<OauthRole> getRoleByUserId(String userId);

}
