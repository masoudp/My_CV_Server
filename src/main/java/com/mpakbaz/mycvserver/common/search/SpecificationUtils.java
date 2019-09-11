package com.mpakbaz.mycvserver.common.search;


import com.google.common.base.Joiner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SpecificationUtils<T> {

    public Specification<T> getSpecification(String search) {
        Specification<T> spec = null;
        if (!StringUtils.isEmpty(search)) {
            ModelSpecificationsBuilder<T> builder = new ModelSpecificationsBuilder<>();
            String operationSetExper = Joiner.on("|")
                    .join(SearchOperation.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile(String.format("(\\w+?)(%s)(\\p{Punct}?)([`\\u0600-\\u06FF\\w]+?)(\\p{Punct}?),", operationSetExper));

            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                boolean isOr = false;
                try {
                    if (matcher.start() > 0)
                        isOr = search.charAt(matcher.start() - 1) == '|' || search.charAt(matcher.start() - 2) == '|';
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isOr)
                    builder.with("'", matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
                else
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
            }

            try {
                spec = builder.build().specification;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spec;
    }

    public SpecRet<T> getSpecificationAndQuery(String search) {
        SpecRet<T> spec = null;
        if (!StringUtils.isEmpty(search)) {
            ModelSpecificationsBuilder<T> builder = new ModelSpecificationsBuilder<>();
            String operationSetExper = Joiner.on("|")
                    .join(SearchOperation.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile(String.format("(\\w+?)(%s)(\\p{Punct}?)([\\u0600-\\u06FF\\w]+?)(\\p{Punct}?),", operationSetExper));

            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                boolean isOr = false;
                try {
                    isOr = search.charAt(matcher.start() - 1) == '|' || search.charAt(matcher.start() - 2) == '|';
                } catch (Exception e) {
                    isOr = false;//e.printStackTrace();
                }
                if (isOr)
                    builder.with("'", matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
                else
                    builder.with( matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
            }

            spec = builder.build();
        }
        return spec;
    }
}
