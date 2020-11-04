package com.spadea.xqipao.data.even;

public class HoldingWheatEvent {


    public HoldingWheatEvent(String userId) {
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
