package com.mpakbaz.mycvserver.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;

    @NotNull(message="User.Username.NotNull")
    @Size(min = 4, max = 50,  message="User.Username.Size")
    @Email(message="User.Email.notValid")
    private String username;

    @Size(min = 6, max = 20, message="User.Password.Size")
    @NotNull(message="User.Password.NotNull")
    private String password;
}
