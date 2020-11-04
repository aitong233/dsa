package com.spadea.xqipao.data;

public class ManageRoomModel {


    /**
     * room_id :
     * room_name :
     * cover_picture :
     * popularity :
     * is_password :
     * role :
     */

    /**
     * {
     *             "room_id": "",
     *             "room_name": "",
     *             "cover_picture": "",
     *             "popularity": "",
     *             "nickname": "",
     *             "is_password": ""
     *         }
     */

    private String room_id;
    private String room_name;
    private String cover_picture;
    private String popularity;
    private String is_password;
    private String role;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
