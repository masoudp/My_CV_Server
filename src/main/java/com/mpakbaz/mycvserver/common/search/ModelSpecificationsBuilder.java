package com.mpakbaz.mycvserver.common.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

//https://github.com/eugenp/tutorials/blob/1c9477390d9c05fb6b651883f8c117d04261c66c/spring-rest-query-language/src/main/java/org/baeldung/persistence/dao/UserSpecificationsBuilder.java

public final class ModelSpecificationsBuilder<T> {

    private final List<SpecSearchCriteria> params;

    public ModelSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    // API

    public final ModelSpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final ModelSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }


    public SpecRet build() {

        if (params.size() == 0)
            return null;

        Specification<T> result = new ModelSpecification<T>(params.get(0));

        String query = "";
        for (int i = 0; i < params.size(); i++) {
            SpecSearchCriteria param = params.get(i);
            String qq ="lower(" +param.getKey()+")";
            switch (param.getOperation()) {
                case ENDS_WITH:
                    qq += " LIKE '" + param.getValue().toString().toLowerCase() + "%'";
                    break;
                case LIKE:
                    qq += " LIKE '" + param.getValue().toString().toLowerCase() + "'";
                    break;
                case STARTS_WITH:
                    qq += " LIKE '%" + param.getValue().toString().toLowerCase() + "'";
                    break;
                case EQUALITY:
                    qq += " = " + param.getValue().toString().toLowerCase() + "";
                    break;
                case NEGATION:
                    qq += " <> " + param.getValue().toString().toLowerCase() + "";
                    break;
                case LESS_THAN:
                    qq += " < " + param.getValue() + "";
                    break;
                case GREATER_THAN:
                    qq += " > " + param.getValue() + "";
                    break;
                case CONTAINS:
                    qq += " LIKE '%" + param.getValue().toString().toLowerCase() + "%'";
                    break;
            }
            if (i > 0) {
                if (param.isOrPredicate()) {
                    query = query + " OR ( " + qq + ")";
                } else {
                    query = query + " AND ( " + qq + ")";
                }
            } else
                query = qq;
        }


        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specifications.where(result)
                    .or(new ModelSpecification<T>(params.get(i)))
                    : Specifications.where(result)
                    .and(new ModelSpecification<T>(params.get(i)));

        }

        return new SpecRet(result, query);
    }

    public final ModelSpecificationsBuilder with(ModelSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final ModelSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
