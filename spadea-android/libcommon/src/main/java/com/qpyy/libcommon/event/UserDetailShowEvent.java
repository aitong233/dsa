package com.qpyy.libcommon.event;

public class UserDetailShowEvent {
    public String roomId;
    public String userId;

    public UserDetailShowEvent(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }
}
