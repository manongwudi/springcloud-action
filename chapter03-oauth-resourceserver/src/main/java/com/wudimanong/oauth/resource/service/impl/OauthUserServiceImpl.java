package com.wudimanong.oauth.resource.service.impl;

import com.wudimanong.oauth.entity.model.OauthUser;
import com.wudimanong.oauth.resource.dao.mapper.OauthUserDao;
import com.wudimanong.oauth.resource.service.OauthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Slf4j
@Service
public class OauthUserServiceImpl implements OauthUserService {

    @Autowired
    OauthUserDao oauthUserDao;

    @Override
    public OauthUser selectOneByUserName(String userName) {
        OauthUser oauthUser = null;
        try {
            oauthUser = oauthUserDao.selectOneByUserName(userName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return oauthUser;
    }
}
