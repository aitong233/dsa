package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/6 13:37
 * 描述 describe
 */
public class RoomPitUserModel {
    /**
     * user_id : 22
     * pit_number : 9
     * nickname : 提莫队长
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2020-03-12/52A97004-7F9D-4242-836A-A066C615AEC6.png
     */

    private String user_id;
    private String pit_number;
    private String nickname;
    private String head_picture;
    private boolean isSelect = false;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPit_number() {
        return pit_number;
    }

    public void setPit_number(String pit_number) {
        this.pit_number = pit_number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }
}
