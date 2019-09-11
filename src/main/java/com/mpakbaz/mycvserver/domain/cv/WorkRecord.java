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
public class WorkRecord extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 9073989042949647151L;
    /**
     * company : Your employer name
     * position : Your job title
     * website : The URL for the employer's website
     * startDate : Your start date, in YYYY-MM-DD format
     * endDate : Your end date, in YYY-MM-DD format (leave blank for a current position)
     * summary : A one-sentence to one-paragraph summary of this employer or position
     * highlights : ["Bullet-point list items that you would like to include along with (or instead of) a summary paragraph."]
     */

    private String company;
    private String position;
    private String website;
    private String startDate;
    private String endDate;
    private String summary;
//    private List<String> highlights;
}