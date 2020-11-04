package com.qpyy.module.index.bean;

import java.util.List;

public class SearchResp {


    private List<UserResultResp> user_result;
    private RoomResultResp room_result;

    public List<UserResultResp> getUser_result() {
        return user_result;
    }

    public void setUser_result(List<UserResultResp> user_result) {
        this.user_result = user_result;
    }

    public RoomResultResp getRoom_result() {
        return room_result;
    }

    public void setRoom_result(RoomResultResp room_result) {
        this.room_result = room_result;
    }
}
