package com.mpakbaz.mycvserver.security.service;

import com.mpakbaz.mycvserver.domain.Authority;
import com.mpakbaz.mycvserver.domain.enums.SocialStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;


@Getter
@AllArgsConstructor()
@Builder
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final Long id;
    private final String token;
    private final String fullName;
    private final String avatar;
    private final SocialStatus status;
    private final List<Authority> authorities;


}
