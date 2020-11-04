package com.qpyy.room.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/9 7:26 PM
 * 描述 describe
 */
public class ProtectedRankingItemBean {
    private String user_id;
    private String type_name;
    private  String nickname;
    private int days;
    private String head_picture;
    private String expired_time;
    private String type;
    private List<ProtectedItemBean> protect_info;

    public List<ProtectedItemBean> getProtect_info() {
        return protect_info;
    }

    public void setProtect_info(List<ProtectedItemBean> protect_info) {
        this.protect_info = protect_info;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public String getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(String expired_time) {
        this.expired_time = expired_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class TypeItem{
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
