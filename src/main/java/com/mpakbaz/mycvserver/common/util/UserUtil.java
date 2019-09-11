package com.mpakbaz.mycvserver.common.util;

import com.mpakbaz.mycvserver.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ModelMapper().map(authentication.getPrincipal(), User.class);
    }
}
