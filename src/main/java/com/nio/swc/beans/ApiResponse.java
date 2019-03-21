package com.nio.swc.beans;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Data
public class ApiResponse {
    /**
     * 日期
     */
    protected Date timestamp;
    /**
     * http响应状态
     */
    protected int status;
    /**
     * URI路径
     */
    protected String path;
    /**
     * 获得数据
     */
    protected Object data;

    public ApiResponse(Object data) {
        timestamp = new Date();
        status = HttpStatus.OK.value();
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        path = builder.build().toUri().getPath();
        this.data = data;
    }
}
