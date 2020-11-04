package com.spadea.xqipao.data.socket;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data.socket
 * 创建人 王欧
 * 创建时间 2020/4/14 11:30 AM
 * 描述 describe
 */
public class RoomInfoModel {
    private String room_id;
    private String picture;
    private String playing;

    public String getPlaying() {
        return playing;
    }

    public void setPlaying(String playing) {
        this.playing = playing;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
