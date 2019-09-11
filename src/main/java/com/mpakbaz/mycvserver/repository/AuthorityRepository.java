package com.mpakbaz.mycvserver.repository;

import com.mpakbaz.mycvserver.domain.Authority;
import com.mpakbaz.mycvserver.domain.enums.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName authorityName);
}
