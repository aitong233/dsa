package com.spadea.xqipao.data;

public class ProfitModel {


    /**
     * user_id :
     * today :
     * week :
     * month :
     * last_month :
     * earning :
     * room_id :
     */

    private String user_id;
    private String today;
    private String week;
    private String month;
    private String last_month;
    private String earning;
    private String room_id;
    private String room_earning;

    public String getRoom_earning() {
        return room_earning;
    }

    public void setRoom_earning(String room_earning) {
        this.room_earning = room_earning;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLast_month() {
        return last_month;
    }

    public void setLast_month(String last_month) {
        this.last_month = last_month;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
