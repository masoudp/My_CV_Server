package com.mpakbaz.mycvserver.DTO;

import com.mpakbaz.mycvserver.domain.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDOT extends BaseDTO  implements Serializable {

    private static final long serialVersionUID = 373136312314504132L;

    private String email;

    private String fullName;

    private String token;

    private UserType userType;
}
