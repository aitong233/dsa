package com.spadea.xqipao.data.socket;

public class RoomBannedModel {

    /**
     * room_id, user_id, action(1禁言2解禁)
     */

    private String room_id;
    private String user_id;
    private String action;
    private String pit_number;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
