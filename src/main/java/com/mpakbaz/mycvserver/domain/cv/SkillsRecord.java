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
    public  class SkillsRecord   extends DomainEntity implements Serializable {
        /**
         * name : A category of job skills (e.g. 'Programming Languages')
         * level : 
         * keywords : ["Keywords under this category (e.g. 'Java', 'C++', etc)"]
         */

        private String name;
        private String level;
//        private List<String> keywords;
    }