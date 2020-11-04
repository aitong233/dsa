package com.qpyy.module.index.bean;

import android.text.TextUtils;

public class RecommendAttentionResp {

    private String room_id;
    private String room_name;
    private String label_id;
    private String type_id;
    private String popularity;
    private String owner_picture;
    private String holder;
    private String holder_picture;
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

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOwner_picture() {
        return owner_picture;
    }

    public void setOwner_picture(String owner_picture) {
        this.owner_picture = owner_picture;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolder_picture() {
        return holder_picture;
    }

    public void setHolder_picture(String holder_picture) {
        this.holder_picture = holder_picture;
    }
}
