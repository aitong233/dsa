package com.qpyy.module.index.bean;

import java.util.List;

public class RoomResultResp {

    private int count;
    private List<RoomResultInfo> list;

    public static class RoomResultInfo {
        private String room_id;
        private String room_code;
        private String room_name;
        private String label_name;
        private String popularity;
        private String is_password;
        private String nickname;
        private String cover_picture;
        private String label_icon;
        private int locked;

        public int getLocked() {
            return locked;
        }

        public void setLocked(int locked) {
            this.locked = locked;
        }

        public String getLabel_icon() {
            return label_icon;
        }

        public void setLabel_icon(String label_icon) {
            this.label_icon = label_icon;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_code() {
            return room_code;
        }

        public void setRoom_code(String room_code) {
            this.room_code = room_code;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getLabel_name() {
            return label_name;
        }

        public void setLabel_name(String label_name) {
            this.label_name = label_name;
        }

        public String getPopularity() {
            return popularity;
        }

        public void setPopularity(String popularity) {
            this.popularity = popularity;
        }

        public String getIs_password() {
            return is_password;
        }

        public void setIs_password(String is_password) {
            this.is_password = is_password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RoomResultInfo> getList() {
        return list;
    }

    public void setList(List<RoomResultInfo> list) {
        this.list = list;
    }
}
