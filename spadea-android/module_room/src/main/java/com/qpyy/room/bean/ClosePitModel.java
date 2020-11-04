package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/18 20:58
 * 描述 describe
 */
public class ClosePitModel {
    /**
     * room_id : 3
     * pit_number : 7
     * action : 1
     */

    private String room_id;
    private String pit_number;
    private String action;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getPit_number() {
        return pit_number;
    }

    public void setPit_number(String pit_number) {
        this.pit_number = pit_number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
