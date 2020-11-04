package com.qpyy.libcommon.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module_news.bean
 * 创建人 王欧
 * 创建时间 2020/7/14 9:42 AM
 * 描述 describe
 */
public class EmChatUserInfo {

    /**
     * user_id :
     * sex :
     * head_picture :
     * nickname :
     * picture :
     */

    private String user_id;
    private String sex;
    private String head_picture;
    private String nickname;
    private String picture;
    private String is_black;

    public String getIs_black() {
        return is_black;
    }

    public void setIs_black(String is_black) {
        this.is_black = is_black;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
