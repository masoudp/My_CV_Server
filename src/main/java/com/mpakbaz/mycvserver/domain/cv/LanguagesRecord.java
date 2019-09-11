package com.mpakbaz.mycvserver.domain.cv;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Table
@Entity
public class LanguagesRecord   extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -1531161152554931058L;
    /**
     * language : Language name
     * fluency : Your language fluency
     */

    private String language;
    private String fluency;
}