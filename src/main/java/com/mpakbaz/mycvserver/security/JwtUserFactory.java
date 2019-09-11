package com.mpakbaz.mycvserver.security;


import com.mpakbaz.mycvserver.domain.User;
import com.mpakbaz.mycvserver.domain.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
//                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        try {
            return authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
