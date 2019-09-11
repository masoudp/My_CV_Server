package com.mpakbaz.mycvserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Calendar;


@MappedSuperclass
@Inheritance
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;


    @CreatedDate
    Long created;

    @LastModifiedDate
    Long updated;


    @PrePersist
    public void onPrePersist() {
        audit("INSERT");
    }

    @PreUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }

    @PreRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    private void audit(String operation) {
        if (operation.equals("INSERT")) {
            setCreated(Calendar.getInstance().getTimeInMillis());
            setUpdated(Calendar.getInstance().getTimeInMillis());
        } else if (operation.equals("UPDATE"))
            setUpdated(Calendar.getInstance().getTimeInMillis());
    }


}
