package com.wudimanong.oauth.service;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
@Slf4j
public class UsernameUserDetailService extends AbstractUserDetailService {

    @Override
    public OauthUser getUser(String username) throws UsernameNotFoundException {
        ResponseData<OauthUser> oauthUserResponseData = oauthResourceClient.getUserByUserName(username);
        if (oauthUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode()
                .equals(oauthUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return oauthUserResponseData.getData();
    }
}
