package com.spadea.xqipao.data.socket;

public class PopularityModel {
    /**
     * room_id : 5
     * user_id : 2
     * popularity : 60
     */

    private String room_id;
    private int user_id;
    private String popularity;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}
