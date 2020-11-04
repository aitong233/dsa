package com.qpyy.libcommon.event;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.event
 * 创建人 王欧
 * 创建时间 2020/8/17 3:47 PM
 * 描述 describe
 */
public class RoomGuardEvent {
    private String room_id;
    private String  nickname_from;
    private String nickname_to;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getNickname_from() {
        return nickname_from;
    }

    public void setNickname_from(String nickname_from) {
        this.nickname_from = nickname_from;
    }

    public String getNickname_to() {
        return nickname_to;
    }

    public void setNickname_to(String nickname_to) {
        this.nickname_to = nickname_to;
    }
}
