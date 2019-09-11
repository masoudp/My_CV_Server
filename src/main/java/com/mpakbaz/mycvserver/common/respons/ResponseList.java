/**
 * This is the common structure for all responses
 * if the response contains a list(array) then it will have 'items' field
 * if the response contains a single item then it will have 'item'  field
 */


package com.mpakbaz.mycvserver.common.respons;


import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseList<T, V> {

    private List<V> listResult;
    private boolean success;
    private int totalPages;
    private boolean last;
    private long totalElements;
    private int numberOfElements;
    private String sort;
    private boolean first;
    private int size;
    private int number;
    private long currentTime;
    private final Class<V> entityDto;



    private V convertToDto(T entity) {
        ModelMapper modelMapper = new ModelMapper();
        V entityDto = modelMapper.map(entity, this.entityDto);
        return entityDto;
    }



    public List<V> getListEntices(List<T> entices) {
        return entices.stream()
                .map(entity -> convertToDto(entity))
                .collect(Collectors.toList());
    }



    public ResponseList(Page<T> listResult, Class<V> entityDto) {
        this.entityDto = entityDto;

        totalPages = listResult.getTotalPages();
        last = listResult.isLast();
        first = listResult.isFirst();
        totalElements = listResult.getTotalElements();
        numberOfElements = listResult.getNumberOfElements();
        sort = listResult.getSort() != null ? listResult.getSort().toString() : null;
        size = listResult.getSize();
        number = listResult.getNumber();
        this.listResult = getListEntices(listResult.getContent()) ;
        success = true;
        currentTime = System.currentTimeMillis();
    }

}
