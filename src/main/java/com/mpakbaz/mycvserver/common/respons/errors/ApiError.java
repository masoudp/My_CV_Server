package com.mpakbaz.mycvserver.common.respons.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {

    private boolean success;
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private long currentTime;


    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        success = false;
        currentTime= System.currentTimeMillis();
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        success = false;
        currentTime= System.currentTimeMillis();
    }
}