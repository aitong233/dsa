package com.qpyy.room.bean;

import com.qpyy.libcommon.bean.RankInfo;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/7/25 11:02
 * 描述 describe
 */
public class OnlineListResp {
    /**
     * user_id :
     * user_code :
     * head_picture :
     * nickname :
     * sex :
     * role :
     * rank_info : [null]
     */

    private String user_id;
    private String user_code;
    private String head_picture;
    private String nickname;
    private String pit_number;
    private String rank_id;
    private String nobility;
    private int sex;
    private int role;
    private RankInfo rank_info;
    private int user_is_new;
    private String nobility_icon;
    private String rank_icon;
    private String good_number;
    private boolean isBaoWheat;

    public boolean isBaoWheat() {
        return isBaoWheat;
    }

    public void setBaoWheat(boolean baoWheat) {
        isBaoWheat = baoWheat;
    }

    public String getPit_number() {
        return pit_number;
    }

    public void setPit_number(String pit_number) {
        this.pit_number = pit_number;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getNobility() {
        return nobility;
    }

    public void setNobility(String nobility) {
        this.nobility = nobility;
    }

    public String getRank_icon() {
        return rank_icon;
    }

    public void setRank_icon(String rank_icon) {
        this.rank_icon = rank_icon;
    }

    public String getGood_number() {
        return good_number;
    }

    public void setGood_number(String good_number) {
        this.good_number = good_number;
    }

    public String getNobility_icon() {
        return nobility_icon;
    }

    public void setNobility_icon(String nobility_icon) {
        this.nobility_icon = nobility_icon;
    }

    public int getUser_is_new() {
        return user_is_new;
    }

    public void setUser_is_new(int user_is_new) {
        this.user_is_new = user_is_new;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
}
