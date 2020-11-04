package com.spadea.xqipao.data;

import java.util.List;

public class MyManageRoomModel {


    /**
     * my : {"room_id":"1102","room_name":"因为一个人爱上一座城","cover_picture":"","popularity":"0","is_password":"0"}
     */

    private MyBean my;
    private List<ManageRoomModel> manager;


    public List<ManageRoomModel> getManager() {
        return manager;
    }

    public void setManager(List<ManageRoomModel> manager) {
        this.manager = manager;
    }

    public MyBean getMy() {
        return my;
    }

    public void setMy(MyBean my) {
        this.my = my;
    }

    public static class MyBean {
        /**
         * room_id : 1102
         * room_name : 因为一个人爱上一座城
         * cover_picture :
         * popularity : 0
         * is_password : 0
         */

        private String room_id;
        private String room_name;
        private String cover_picture;
        private String popularity;
        private String is_password;

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

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
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
    }
}
