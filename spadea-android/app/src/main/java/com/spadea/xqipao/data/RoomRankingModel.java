package com.spadea.xqipao.data;

public class RoomRankingModel {


    /**
     * number : 1
     * name : Ts.维蜜天使等待老公来爱
     * rank : 1
     */

    private String number;
    private String name;
    private int rank;
    private String room_id;
    private String picture;
    private int has_password; //0没有    1有
    private String number_format;

    public String getNumber_format() {
        return number_format;
    }

    public void setNumber_format(String number_format) {
        this.number_format = number_format;
    }

    public int getHas_password() {
        return has_password;
    }

    public void setHas_password(int has_password) {
        this.has_password = has_password;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
