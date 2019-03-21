package com.nio.swc.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * Created by bingwen.shi on 2018/3/23.
 */
@Getter
public class ApiException extends RuntimeException  {

    private final HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
