package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/13 09:18
 * 描述 describe
 */
public class RoomUserInfoResp {


    /**
     * user_id :
     * user_code :
     * nickname :
     * head_picture :
     * rank_id :
     * nobility_id :
     * rank_icon :
     * nobility_picture :
     * good_number :
     * shutup :
     * role :
     * banned :
     * follow :
     * nobility_set : {"color_from":"","color_to":"","txt_color":"","head_color":""}
     */

    private String user_id;
    private String user_code;
    private String nickname;
    private String head_picture;
    private String rank_id;
    private String nobility_id;
    private String rank_icon;
    private String nobility_picture;
    private String good_number;
    private int shutup;
    private String role;
    private Boolean allow_manager;
    private int banned;
    private int follow;
    private String pit_number;
    private String emchat_username;
    private NobilitySetBean nobility_set;

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
    }

    public String getPit_number() {
        return pit_number;
    }

    public void setPit_number(String pit_number) {
        this.pit_number = pit_number;
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

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getNobility_id() {
        return nobility_id;
    }

    public void setNobility_id(String nobility_id) {
        this.nobility_id = nobility_id;
    }

    public String getRank_icon() {
        return rank_icon;
    }

    public void setRank_icon(String rank_icon) {
        this.rank_icon = rank_icon;
    }

    public String getNobility_picture() {
        return nobility_picture;
    }

    public void setNobility_picture(String nobility_picture) {
        this.nobility_picture = nobility_picture;
    }

    public String getGood_number() {
        return good_number;
    }

    public void setGood_number(String good_number) {
        this.good_number = good_number;
    }

    public int getShutup() {
        return shutup;
    }

    public void setShutup(int shutup) {
        this.shutup = shutup;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public NobilitySetBean getNobility_set() {
        return nobility_set;
    }

    public void setNobility_set(NobilitySetBean nobility_set) {
        this.nobility_set = nobility_set;
    }

    public Boolean getAllow_manager() {
        return allow_manager;
    }

    public void setAllow_manager(Boolean allow_manager) {
        this.allow_manager = allow_manager;
    }

    public static class NobilitySetBean {
        /**
         * color_from :
         * color_to :
         * txt_color :
         * head_color :
         */

        private String color_from;
        private String color_to;
        private String txt_color;
        private String head_color;

        public String getColor_from() {
            return color_from;
        }

        public void setColor_from(String color_from) {
            this.color_from = color_from;
        }

        public String getColor_to() {
            return color_to;
        }

        public void setColor_to(String color_to) {
            this.color_to = color_to;
        }

        public String getTxt_color() {
            return txt_color;
        }

        public void setTxt_color(String txt_color) {
            this.txt_color = txt_color;
        }

        public String getHead_color() {
            return head_color;
        }

        public void setHead_color(String head_color) {
            this.head_color = head_color;
        }
    }
}
