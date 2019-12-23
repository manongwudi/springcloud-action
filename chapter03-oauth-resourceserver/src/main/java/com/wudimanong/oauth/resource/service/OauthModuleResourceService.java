package com.wudimanong.oauth.resource.service;

import com.wudimanong.oauth.entity.model.OauthModuleResources;
import java.util.List;

/**
 * @author jiangqiao
 */
public interface OauthModuleResourceService {

    /**
     * 根据用户ID获取用户菜单列表
     *
     * @param userId
     * @return
     */
    List<OauthModuleResources> getMenusByUserId(String userId);

}
