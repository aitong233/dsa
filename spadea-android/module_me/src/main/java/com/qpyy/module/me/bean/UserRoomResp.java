package com.qpyy.module.me.bean;

import android.text.TextUtils;

public class UserRoomResp {


    private String room_code;
    private String room_id;
    private String room_name;
    private String popularity;
    private String label_icon;
    private String is_lock;
    private String head_picture;
    private String on_live;

    private String cover_picture;

    public String getRoomPicture() {
        if (!TextUtils.isEmpty(cover_picture)) {
            return cover_picture;
        }
        return head_picture;
    }

    public String getCover_picture() {
        return cover_picture;
    }

    public void setCover_picture(String cover_picture) {
        this.cover_picture = cover_picture;
    }

    public String getOn_live() {
        return on_live;
    }

    public void setOn_live(String on_live) {
        this.on_live = on_live;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getLabel_icon() {
        return label_icon;
    }

    public void setLabel_icon(String label_icon) {
        this.label_icon = label_icon;
    }

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }
}
