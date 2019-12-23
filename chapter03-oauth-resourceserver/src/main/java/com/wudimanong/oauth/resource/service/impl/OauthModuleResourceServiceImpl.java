package com.wudimanong.oauth.resource.service.impl;

import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.resource.dao.mapper.OauthModuleResourcesDao;
import com.wudimanong.oauth.resource.service.OauthModuleResourceService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Slf4j
@Service
public class OauthModuleResourceServiceImpl implements OauthModuleResourceService {

    @Autowired
    OauthModuleResourcesDao oauthModuleResourcesDao;

    @Override
    public List<OauthModuleResources> getMenusByUserId(String userId) {
        List<OauthModuleResources> oauthModuleResourcesList = null;
        try {
            oauthModuleResourcesList = oauthModuleResourcesDao.getMenusByUserId(userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return oauthModuleResourcesList;
    }
}
