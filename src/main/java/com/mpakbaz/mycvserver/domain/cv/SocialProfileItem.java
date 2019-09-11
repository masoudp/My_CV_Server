package com.mpakbaz.mycvserver.domain.cv;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table
public class SocialProfileItem  extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -6495897986483922204L;
    /**
     * network : A social media or other profile that you would like to include (e.g. LinkedIn, Twitter)
     * username : Your username on this network
     * url : A URL to your user profile page
     */

    private String network;
    private String username;
    private String url;
}