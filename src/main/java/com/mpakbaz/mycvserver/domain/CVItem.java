package com.mpakbaz.mycvserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class CVItem extends DomainEntity {

    @NotNull
    @NotBlank
    private String title;

    private String imageUrl;

    private Integer level;

    private Long parent;
}
