package com.wudimanong.oauth.resource.service.impl;

import com.wudimanong.oauth.entity.model.OauthRole;
import com.wudimanong.oauth.resource.dao.mapper.OauthRoleDao;
import com.wudimanong.oauth.resource.service.OauthRoleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Slf4j
@Service
public class OauthRoleServiceImpl implements OauthRoleService {

    @Autowired
    OauthRoleDao oauthRoleDao;

    @Override
    public List<OauthRole> getRoleByUserId(String userId) {
        List<OauthRole> roleList = null;
        try {
            roleList = oauthRoleDao.getRoleByUserId(userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return roleList;
    }
}
