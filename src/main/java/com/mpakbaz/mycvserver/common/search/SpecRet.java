package com.mpakbaz.mycvserver.common.search;

import org.springframework.data.jpa.domain.Specification;

public class SpecRet<T> {
    public Specification<T> specification;
    public String query;

    public SpecRet(Specification<T> specification, String query) {
        this.specification = specification;
        this.query = query;
    }
}