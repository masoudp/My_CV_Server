package com.mpakbaz.mycvserver.domain;

import com.mpakbaz.mycvserver.domain.enums.Gender;
import com.mpakbaz.mycvserver.domain.enums.LOCALE;
import com.mpakbaz.mycvserver.domain.enums.UserType;
import com.vividsolutions.jts.geom.Geometry;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User extends DomainEntity {

    @Column(name = "username", unique = true)
    @NotNull
    @NotBlank
//    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    private String fullName = "";

    @NotNull
    private UserType userType = UserType.USER;


    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    private LOCALE language;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

}