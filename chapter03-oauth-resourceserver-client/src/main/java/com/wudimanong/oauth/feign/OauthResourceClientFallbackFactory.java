package com.wudimanong.oauth.feign;

import com.wudimanong.oauth.entity.model.OauthClientDetails;
import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.entity.model.OauthRole;
import com.wudimanong.oauth.entity.model.OauthUser;
import feign.hystrix.FallbackFactory;
import java.util.List;

/**
 * @author jiangqiao
 */
public class OauthResourceClientFallbackFactory implements FallbackFactory<OauthResourceClient> {

    @Override
    public OauthResourceClient create(Throwable cause) {
        return new OauthResourceClient() {
            @Override
            public ResponseData<List<OauthClientDetails>> getAllClient() {
                //todo fallback逻辑处理
                return null;
            }

            @Override
            public ResponseData<OauthUser> getUserByUserName(String userName) {
                return null;
            }

            @Override
            public ResponseData<List<OauthRole>> getRoleByUserId(String userId) {
                return null;
            }

            @Override
            public ResponseData<List<OauthModuleResources>> getMenusByUserId(String userId) {
                return null;
            }
        };
    }
}
