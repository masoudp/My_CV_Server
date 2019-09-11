package com.mpakbaz.mycvserver.domain;

import com.mpakbaz.mycvserver.domain.cv.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Table
@Entity
public class CVData extends DomainEntity implements Serializable {


    private static final long serialVersionUID = -8793897717972717372L;
    private String email;
    private BasicData basics;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkRecord> work;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<VolunteerRecord> volunteer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EducationRecord> education;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AwardsRecord> awards;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PublicationsRecord> publications;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SkillsRecord> skills;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<LanguagesRecord> languages;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InterestsRecord> interests;

    @NoArgsConstructor
    @Data
    public static class BasicData implements Serializable {
        private static final long serialVersionUID = -7780477276622953824L;
        /**
         * name : Your first and last name
         * label :
         * picture :
         * email : Your email address
         * phone : A phone number, with any formatting you like. E.g. (555) 555-5555.
         * degree :
         * website : Your website URL
         * summary : A one-sentence to one-paragraph overview text. Do not include any line-breaks.
         * location : {"address":"Your street address or mailing address","postalCode":"Your postal code (ZIP in the U.S.)","city":"Your city","countryCode":"Your country (e.g. USA)","region":"Your region (state in the U.S.)"}
         * profiles : [{"network":"A social media or other profile that you would like to include (e.g. LinkedIn, Twitter)","username":"Your username on this network","url":"A URL to your user profile page"}]
         */

        private String name;
        private String label;
        private String picture;
        private String email;
        private String phone;
        private String degree;
        private String website;
        private String summary;
        @Column(columnDefinition = "TEXT")
        private String bio;
        private LocationItem location;
        private List<SocialProfileItem> profiles;


    }


}
