package com.modo.modo.sportsapp.model.domain.network;

public enum StatusCode {
    PING_SUCCESS(100),
    PING_FAIL(-100),

    AUTH_SUCCESS(200),
    AUTH_FAIL(-200);
    ;

    StatusCode(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
