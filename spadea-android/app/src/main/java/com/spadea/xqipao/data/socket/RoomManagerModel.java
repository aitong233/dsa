package com.spadea.xqipao.data.socket;

public class RoomManagerModel {

    /**
     * room_id, manager_id(管理员用户id)
     */

    private String room_id;
    private String manager_id;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
