package com.wudimanong.oauth.service;

import com.wudimanong.oauth.entity.OauthUserDetail;
import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.entity.model.OauthModuleResources;
import com.wudimanong.oauth.entity.model.OauthRole;
import com.wudimanong.oauth.entity.model.OauthUser;
import com.wudimanong.oauth.feign.OauthResourceClient;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author jiangqiao
 * @desc 用户服务抽象基类
 */
@Slf4j
public abstract class AbstractUserDetailService implements UserDetailsService {

    /**
     * 注入Oauth资源服务器FeignClient调用组件
     */
    @Autowired
    OauthResourceClient oauthResourceClient;

    @Autowired
    RedisTemplate<String, OauthRole> redisTemplate;

    @Autowired
    RedisTemplate<String, OauthModuleResources> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        OauthUser oauthUser = getUser(userName);

        // 调用FeignClient查询用户角色
        ResponseData<List<OauthRole>> baseRoleListResponseData = oauthResourceClient
                .getRoleByUserId(oauthUser.getUserId());
        List<OauthRole> roles;
        if (baseRoleListResponseData.getData() == null || !ResponseCode.SUCCESS.getCode()
                .equals(baseRoleListResponseData.getCode())) {
            log.error("查询角色失败！");
            roles = new ArrayList<>();
        } else {
            roles = baseRoleListResponseData.getData();
        }
        //调用FeignClient查询菜单
        ResponseData<List<OauthModuleResources>> baseModuleResourceListResponseData = oauthResourceClient
                .getMenusByUserId(oauthUser.getUserId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(oauthUser, roles);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 存储菜单到redis
        if (ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode())
                && baseModuleResourceListResponseData.getData() != null) {
            resourcesTemplate.delete(oauthUser.getUserId() + "-menu");
            baseModuleResourceListResponseData.getData().forEach(e -> {
                resourcesTemplate.opsForList().leftPush(oauthUser.getUserId() + "-menu", e);
            });
        }
        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                oauthUser.getUserName(),
                oauthUser.getPassword(), isActive(oauthUser.getActive()), true, true, true, authorities);
        return new OauthUserDetail(oauthUser, user);
    }

    protected abstract OauthUser getUser(String var1);

    private List<GrantedAuthority> convertToAuthorities(OauthUser oauthUser, List<OauthRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        redisTemplate.delete(oauthUser.getUserId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
            authorities.add(authority);
            //存储角色到redis
            redisTemplate.opsForList().rightPush(oauthUser.getUserId(), e);
        });
        return authorities;
    }

    /**
     * 判断用户是否为激活状态
     *
     * @param active
     * @return
     */
    private boolean isActive(int active) {
        return active == 1 ? true : false;
    }
}
