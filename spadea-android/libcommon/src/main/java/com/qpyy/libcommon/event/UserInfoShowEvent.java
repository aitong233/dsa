package com.qpyy.libcommon.event;

public class UserInfoShowEvent {
    public String roomId;
    public String userId;

    public UserInfoShowEvent(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }
}
