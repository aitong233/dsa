package com.qpyy.module.me.bean;

import com.qpyy.libcommon.bean.RankInfo;

public class MyInfoResp {

    private String user_id;
    private String user_code;
    private String head_picture;
    private String sex;
    private String room_id;
    private String room_id_current;
    private String role;
    private String nobility_icon;
    private String level_icon;
    private String friend_count;
    private String follow_count;
    private String fans_count;
    private String visit_count;
    private String user_is_new;
    private int transfer_state;
    private String age;
    private String skill_avatar;
    private String nickname;
    private RankInfo rank_info;
    private String good_number;

    public int getSys_type_id() {
        return sys_type_id;
    }

    public void setSys_type_id(int sys_type_id) {
        this.sys_type_id = sys_type_id;
    }

    private int sys_type_id;

    public String getGood_number() {
        return good_number;
    }

    public void setGood_number(String good_number) {
        this.good_number = good_number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNobility_icon() {
        return nobility_icon;
    }

    public void setNobility_icon(String nobility_icon) {
        this.nobility_icon = nobility_icon;
    }

    public String getLevel_icon() {
        return level_icon;
    }

    public void setLevel_icon(String level_icon) {
        this.level_icon = level_icon;
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

    public String getUser_is_new() {
        return user_is_new;
    }

    public void setUser_is_new(String user_is_new) {
        this.user_is_new = user_is_new;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSkill_avatar() {
        return skill_avatar;
    }

    public void setSkill_avatar(String skill_avatar) {
        this.skill_avatar = skill_avatar;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public int getTransfer_state() {
        return transfer_state;
    }

    public void setTransfer_state(int transfer_state) {
        this.transfer_state = transfer_state;
    }
}
