package com.jonso.security.service.dto;

import com.jonso.security.error.AppErrors;
import lombok.Data;

@Data
public class CommonResult<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;

    //success response
    public CommonResult(T data) {
        this.code = 0;
        this.data = data;
        this.success = true;
        this.message = AppErrors.SUCCESS.name();
    }
    //error response
    public CommonResult(String message, int code) {
        this.code = code;
        this.success = false;
        this.message = message;
    }
}
