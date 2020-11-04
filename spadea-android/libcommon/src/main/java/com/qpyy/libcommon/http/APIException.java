package com.qpyy.libcommon.http;


public class APIException extends RuntimeException {
    private int code;


    public APIException(String message) {
        super(message);
    }

    public APIException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
