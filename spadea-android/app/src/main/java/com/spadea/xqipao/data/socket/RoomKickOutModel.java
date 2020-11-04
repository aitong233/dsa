package com.spadea.xqipao.data.socket;

import com.spadea.xqipao.data.RoomPitBean;

import java.util.List;

public class RoomKickOutModel {

    private String room_id;
    private String user_id;
    private String popularity;
    private int action;
    private List<RoomPitBean> pit_info;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public List<RoomPitBean> getPit_info() {
        return pit_info;
    }

    public void setPit_info(List<RoomPitBean> pit_info) {
        this.pit_info = pit_info;
    }
}
