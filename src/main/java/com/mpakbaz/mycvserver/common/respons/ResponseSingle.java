/**
 * This is the common structure for all responses
 * if the response contains a list(array) then it will have 'items' field
 * if the response contains a single item then it will have 'item'  field
 */


package com.mpakbaz.mycvserver.common.respons;


import lombok.Data;

@Data
public class ResponseSingle<T> {

    private T result;
    private boolean success;
    private long currentTime;


    public ResponseSingle(T result) {
        this.result = result;
        success = true;
        currentTime = System.currentTimeMillis();
    }
}
