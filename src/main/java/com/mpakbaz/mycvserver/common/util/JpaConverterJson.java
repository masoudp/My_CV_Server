package com.mpakbaz.mycvserver.common.util;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.Objects;

@javax.persistence.Converter
public class JpaConverterJson implements AttributeConverter<Object, Object> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object convertToDatabaseColumn(Object attribute) {
        try {
            if (attribute == null)
                return "";


            return objectMapper.writeValueAsString(attribute);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }


    @Override
    public Object convertToEntityAttribute(Object data) {
        try {
            if (Objects.isNull(data))
                return null;

            return data;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
