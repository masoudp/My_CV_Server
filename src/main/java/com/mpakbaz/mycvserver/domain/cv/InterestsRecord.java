package com.mpakbaz.mycvserver.domain.cv;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class InterestsRecord  extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 7231200096329796696L;
    /**
     * name : A category of interests (e.g. 'Sports')
     * keywords : ["Keywords under this category (e.g. 'Cricket', 'Football', 'Golf')"]
     */

    private String name;
//    private List<String> keywords;
}