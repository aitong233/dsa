package com.qpyy.libcommon.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.bean
 * 创建人 王欧
 * 创建时间 2020/8/16 4:08 PM
 * 描述 describe
 */
public class RoomFaceModel {

    /**
     * room_id : 3040
     * pit_number : 7
     * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/5NjmFw3AGE.jpg
     * special : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/ayfep8p4Pi.jpg
     */

    private String room_id;
    private String pit_number;
    private String picture;
    private String special;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
