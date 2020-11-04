package com.spadea.xqipao.data.socket;

import com.spadea.xqipao.data.RoomPitBean;

import java.util.List;

public class RoomWheatModel {

    private String room_id;
    private String user_id;
    private String pit_number;
    private String count;
    private List<RoomPitBean> pit_info;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPit_number() {
        return pit_number;
    }

    public void setPit_number(String pit_number) {
        this.pit_number = pit_number;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public List<RoomPitBean> getPit_info() {
        return pit_info;
    }

    public void setPit_info(List<RoomPitBean> pit_info) {
        this.pit_info = pit_info;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
