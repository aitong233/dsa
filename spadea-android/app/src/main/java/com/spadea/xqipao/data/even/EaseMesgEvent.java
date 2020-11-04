package com.spadea.xqipao.data.even;

public class EaseMesgEvent {


    public EaseMesgEvent(String userId) {
        this.userId = userId;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
