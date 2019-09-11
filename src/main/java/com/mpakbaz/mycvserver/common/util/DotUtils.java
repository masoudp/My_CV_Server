package com.mpakbaz.mycvserver.common.util;

import com.mpakbaz.mycvserver.domain.DomainEntity;
import org.modelmapper.ModelMapper;

public class DotUtils<T extends DomainEntity, V extends Object>  {
    private final Class<V> entityDto ;
    private final Class<T> entityClass;

    public  V convertToDto(T entity) {
        ModelMapper modelMapper = new ModelMapper();
        V entityDto = modelMapper.map(entity, this.entityDto);
        return entityDto;
    }


    public T  convertFromDto(V entityDto) {
        ModelMapper modelMapper = new ModelMapper();
        T entity = modelMapper.map(entityDto, this.entityClass);
        return entity;
    }

    public DotUtils(Class<V> entityDto, Class<T> entityClass) {
        this.entityDto = entityDto;
        this.entityClass = entityClass;
    }
}
