package com.qpyy.module.index.bean;

import android.text.TextUtils;

public class MyFootResp {

    private String room_name;
    private String room_code;
    private String popularity;
    private String label_name;
    private String label_icon;
    private String owner_picture;
    private String owner_nickname;
    private String room_id;
    private int locked;

    private String cover_picture;

    public String getRoomPicture() {
        if (!TextUtils.isEmpty(cover_picture)) {
            return cover_picture;
        }
        return owner_picture;
    }

    public String getCover_picture() {
        return cover_picture;
    }

    public void setCover_picture(String cover_picture) {
        this.cover_picture = cover_picture;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getLabel_icon() {
        return label_icon;
    }

    public void setLabel_icon(String label_icon) {
        this.label_icon = label_icon;
    }

    public String getOwner_picture() {
        return owner_picture;
    }

    public void setOwner_picture(String owner_picture) {
        this.owner_picture = owner_picture;
    }

    public String getOwner_nickname() {
        return owner_nickname;
    }

    public void setOwner_nickname(String owner_nickname) {
        this.owner_nickname = owner_nickname;
    }
}
