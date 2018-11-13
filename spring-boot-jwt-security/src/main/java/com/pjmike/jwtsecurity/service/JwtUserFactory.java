package com.pjmike.jwtsecurity.service;

import com.pjmike.jwtsecurity.entity.Authority;
import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pjmike
 * @create 2018-10-04 17:39
 */
public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getEnabled(),
                user.getLastPasswordResetDate(),
                grantedAuthorities(user.getAuthorities())
                );
    }

    /**
     * 将权限信息保存在 GrantedAuthority,后续进行权限验证时会使用 GrantedAuthority对象
     *
     * @param authorities
     * @return
     */
    public static List<GrantedAuthority> grantedAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName().name());
            grantedAuthorities.add(grantedAuthority);

        }
        return grantedAuthorities;
    }
}
