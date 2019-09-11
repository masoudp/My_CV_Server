package com.mpakbaz.mycvserver.common.search;


import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ModelSpecification<T> implements Specification<T> {

    private SpecSearchCriteria criteria;

    public ModelSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {

        Object value = criteria.getValue();
        Expression<String> x = root.get(criteria.getKey());
        if (value instanceof String) {
            x = builder.lower(x);
            value = ((String) value).toLowerCase();
        }

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal( root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(x, value);
            case GREATER_THAN:
                return builder.greaterThan( root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan( root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(x, value.toString());
            case STARTS_WITH:
                return builder.like(x, value + "%");
            case ENDS_WITH:
                return builder.like(x, "%" + value);
            case CONTAINS:
                return builder.like(x, "%" + value + "%");
            default:
                return null;
        }
    }

}
