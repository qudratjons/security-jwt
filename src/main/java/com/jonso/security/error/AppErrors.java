package com.jonso.security.error;

public enum AppErrors {
    SUCCESS(0),
    UNKNOWN_ERROR(-1),
    EMAIL_NOT_VALID(100),
    PASSWORD_NOT_VALID(101),
    USER_ALREADY_EXISTS(102),
    USER_NOT_FOUND(103)

    ;
    private final int code;

    AppErrors(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
