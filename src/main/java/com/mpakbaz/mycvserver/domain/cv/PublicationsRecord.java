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
public class PublicationsRecord extends DomainEntity implements Serializable {
    private static final long serialVersionUID = 4813861146408692368L;
    /**
     * name : Your publication title
     * publisher : Publisher name
     * releaseDate : Publication date, in YYYY-MM-DD format
     * website : The website URL for this publisher or book
     * summary : A one-sentence to one-paragraph overview of this publication
     */

    private String name;
    private String publisher;
    private String releaseDate;
    private String website;
    private String summary;
}