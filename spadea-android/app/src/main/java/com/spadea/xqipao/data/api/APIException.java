package com.spadea.xqipao.data.api;


public class APIException extends RuntimeException {
    private int code;
    private boolean isJava;



    public APIException(String message) {
        super(message);
    }

    public APIException(int code, String message) {
        super(message);
        this.code = code;
    }

    public APIException(int code, boolean isJava,String message) {
        super(message);
        this.code = code;
        this.isJava=isJava;
    }

    public boolean isJava() {
        return isJava;
    }

    public void setJava(boolean java) {
        isJava = java;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
