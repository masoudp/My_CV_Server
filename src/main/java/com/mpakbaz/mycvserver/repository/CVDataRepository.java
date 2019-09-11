package com.mpakbaz.mycvserver.repository;

import com.mpakbaz.mycvserver.domain.CVData;
import com.mpakbaz.mycvserver.domain.CVItem;

import javax.transaction.Transactional;

@Transactional
public interface CVDataRepository extends BaseRepository<CVData> {
    CVData findByEmail(String email);
}
