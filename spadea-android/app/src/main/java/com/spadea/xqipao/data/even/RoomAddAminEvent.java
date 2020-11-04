package com.spadea.xqipao.data.even;

public class RoomAddAminEvent {

    /**
     * type 0 添加  1删除
     */

    private int type;
    private String userId;


    public RoomAddAminEvent(int type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
