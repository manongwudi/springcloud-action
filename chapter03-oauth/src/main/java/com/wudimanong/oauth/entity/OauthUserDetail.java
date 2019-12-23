package com.wudimanong.oauth.entity;

import com.wudimanong.oauth.entity.model.OauthUser;
import java.util.Collection;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jiangqiao
 * @desc 包装org.springframework.security.core.userdetails.User类; 新增oauthUser用于生成jwt的用户信息
 */
public class OauthUserDetail implements UserDetails, CredentialsContainer {


    private final OauthUser oauthUser;
    private final org.springframework.security.core.userdetails.User user;

    public OauthUserDetail(OauthUser oauthUser, User user) {
        this.oauthUser = oauthUser;
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public OauthUser getOauthUser() {
        return oauthUser;
    }
}
