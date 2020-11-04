package com.qpyy.room.bean;

import java.io.Serializable;

public class RoomOwnerBean implements Serializable {


    /**
     * user_id : 551686
     * user_code : 884003
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/551686/20200221200911_1582286951202590.jpg
     * sex : 2
     * nickname : 秋水（做我家的崽）
     */

    private String user_id;
    private String user_code;
    private String head_picture;
    private String sex;
    private String nickname;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
