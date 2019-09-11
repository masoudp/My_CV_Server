package com.mpakbaz.mycvserver.common.util;

import org.springframework.data.domain.Sort;

public class QueryUtils {

    public static String convertPageableToSortString(Sort sort) {
        if (sort != null) {
            StringBuilder orderClause = new StringBuilder();
            int count = 0;

            for (Sort.Order order : sort) {
                orderClause.append(count++ > 0 ? ", " : "");
                orderClause.append(String.format("%1$s %2$s", order.getProperty(), order.getDirection()));
            }
            return orderClause.toString();
        }
        else
            return "";

    }

}
