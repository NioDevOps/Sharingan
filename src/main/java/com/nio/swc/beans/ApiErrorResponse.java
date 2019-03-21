package com.nio.swc.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data()
@EqualsAndHashCode(callSuper = true)
public class ApiErrorResponse extends ApiResponse {

    /**
     * 错误原因
     */
    private String error;
    /**
     * 消息
     */
    private String message;

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        super(null);
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public ApiErrorResponse(String message) {
        super(null);
        status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.message = message;
    }
}
