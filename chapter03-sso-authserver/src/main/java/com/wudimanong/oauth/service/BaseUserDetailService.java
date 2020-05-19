package com.wudimanong.oauth.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jiangqiao
 */
@Service
public class BaseUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //todo
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 返回带有用户权限信息的User
        User user = new User("jiangqiao",
                "123456", true, true, true, true,
                authorities);
        return user;
    }
}
