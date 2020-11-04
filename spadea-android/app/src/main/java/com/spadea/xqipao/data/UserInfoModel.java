package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class UserInfoModel {


    /**
     * user_id :
     * user_code :
     * head_picture :
     * nickname :
     * sex :
     * rank_id :
     * room_id :
     * room_id_current :
     * friend_count :
     * follow_count :
     * fans_count :
     * visit_count :
     */

    private String user_id;
    private String user_code;
    private String head_picture;
    private String nickname;
    private String sex;
    private String rank_id;
    private String room_id;
    private String room_id_current;
    private String friend_count;
    private String follow_count;
    private String fans_count;
    private String visit_count;
    private RankInfo rank_info;
    private int role;
    private int user_is_new;

    public int getUser_is_new() {
        return user_is_new;
    }

    public void setUser_is_new(int user_is_new) {
        this.user_is_new = user_is_new;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_id_current() {
        return room_id_current;
    }

    public void setRoom_id_current(String room_id_current) {
        this.room_id_current = room_id_current;
    }

    public String getFriend_count() {
        return friend_count;
    }

    public void setFriend_count(String friend_count) {
        this.friend_count = friend_count;
    }

    public String getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(String follow_count) {
        this.follow_count = follow_count;
    }

    public String getFans_count() {
        return fans_count;
    }

    public void setFans_count(String fans_count) {
        this.fans_count = fans_count;
    }

    public String getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(String visit_count) {
        this.visit_count = visit_count;
    }
}
