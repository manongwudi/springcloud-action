package com.wudimanong.oauth.resource.service.impl;

import com.wudimanong.oauth.entity.model.OauthClientDetails;
import com.wudimanong.oauth.resource.dao.mapper.OauthClientDetailsDao;
import com.wudimanong.oauth.resource.service.OauthClientDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    @Autowired
    OauthClientDetailsDao oauthClientDetailsDao;

    @Override
    public List<OauthClientDetails> selectAll() {
        return oauthClientDetailsDao.selectAll();
    }
}
