package com.mpakbaz.mycvserver.repository;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends DomainEntity> extends
        JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
