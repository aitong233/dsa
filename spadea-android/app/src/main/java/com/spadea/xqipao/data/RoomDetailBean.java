package com.spadea.xqipao.data;

public class RoomDetailBean {

    private RoomBean room_info;
    private RoomOwnerBean owner_info;
    private RoomUserBean user_info;

    public RoomBean getRoom_info() {
        return room_info;
    }

    public void setRoom_info(RoomBean room_info) {
        this.room_info = room_info;
    }

    public RoomOwnerBean getOwner_info() {
        return owner_info;
    }

    public void setOwner_info(RoomOwnerBean owner_info) {
        this.owner_info = owner_info;
    }

    public RoomUserBean getUser_info() {
        return user_info;
    }

    public void setUser_info(RoomUserBean user_info) {
        this.user_info = user_info;
    }
}
