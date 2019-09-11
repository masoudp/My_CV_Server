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
@Table
@Entity
public class EducationRecord extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 6138948232762616065L;
    /**
     * institution : Your school name
     * area : Your area of study or degree earned
     * studyType :
     * startDate : Your start date, in YYYY-MM-DD format
     * endDate : Your completion date, in YYYY-MM-DD format
     * gpa :
     * courses : [""]
     */

    private String institution;
    private String area;
    private String studyType;
    private String startDate;
    private String endDate;
    private String gpa;
//    private List<String> courses;
}