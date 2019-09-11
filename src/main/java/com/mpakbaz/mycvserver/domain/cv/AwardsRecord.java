package com.mpakbaz.mycvserver.domain.cv;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table
public class AwardsRecord extends DomainEntity implements Serializable {
    private static final long serialVersionUID = -289200579348715297L;
    /**
     * title : Your award title
     * date : Your date, in YYYY-MM-DD format you received the award
     * awarder : Your award given by
     * summary : A one-sentence to one-paragraph overview of this award
     */

    private String title;
    private String date;
    private String awarder;
    private String summary;
}