package com.spadea.xqipao.data.socket;

public class RoomPasswordModel {

    /**
     * room_id, action 1删除密码2修改密码
     */

    private String room_id;
    private String action;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
