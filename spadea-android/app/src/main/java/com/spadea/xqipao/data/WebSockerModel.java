package com.spadea.xqipao.data;

public class WebSockerModel {
    private int code;
    private String id;
    private String data;


    public WebSockerModel(int code, String id, String data) {
        this.code = code;
        this.id = id;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
