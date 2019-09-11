package com.mpakbaz.mycvserver.DTO;

import com.mpakbaz.mycvserver.domain.cv.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CVDataDTO extends BaseDTO implements Serializable {


    private String email;
    private BasicData basics;
    private List<WorkRecord> work;
    private List<VolunteerRecord> volunteer;
    private List<EducationRecord> education;
    private List<AwardsRecord> awards;
    private List<PublicationsRecord> publications;
    private List<SkillsRecord> skills;
    private List<LanguagesRecord> languages;
    private List<InterestsRecord> interests;

    @NoArgsConstructor
    @Data
    public static class BasicData implements Serializable {
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
        private String bio;
        private LocationItem location;
        private List<SocialProfileItem> profiles;


    }


}
