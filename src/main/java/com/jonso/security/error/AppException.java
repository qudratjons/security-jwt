package com.jonso.security.error;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

    private final AppErrors appErrors;

    public AppException(AppErrors appErrors) {
        super(appErrors.name());
        this.appErrors = appErrors;
    }
}
