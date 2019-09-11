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
public class LocationItem  extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -1401520821639830376L;
    /**
     * address : Your street address or mailing address
     * postalCode : Your postal code (ZIP in the U.S.)
     * city : Your city
     * countryCode : Your country (e.g. USA)
     * region : Your region (state in the U.S.)
     */

    private String address;
    private String postalCode;
    private String city;
    private String countryCode;
    private String region;
}