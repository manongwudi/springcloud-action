package com.wudimanong.oauth.feign;

import com.wudimanong.oauth.entity.model.OauthClientDetails;
import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.entity.model.OauthRole;
import com.wudimanong.oauth.entity.model.OauthUser;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jiangqiao
 */
@FeignClient(value = "oauth-resourceserver", configuration = OauthResourceClientConfiguration.class, fallbackFactory = OauthResourceClientFallbackFactory.class)
public interface OauthResourceClient {

    /**
     * 获取客户端应用列表信息
     *
     * @return
     */
    @RequestMapping(value = "/internel/client/all", method = RequestMethod.GET)
    ResponseData<List<OauthClientDetails>> getAllClient();

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/internel/user/name/{userName}", method = RequestMethod.GET)
    ResponseData<OauthUser> getUserByUserName(@PathVariable("userName") String userName);


    /**
     * 根据userId查询角色
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/internel/role/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<OauthRole>> getRoleByUserId(@PathVariable("userId") String userId);


    /**
     * 根据userId查询菜单
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/internel/menu/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<OauthModuleResources>> getMenusByUserId(@PathVariable("userId") String userId);
}
