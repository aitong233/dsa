package com.qpyy.room.bean;

import com.qpyy.libcommon.bean.RankInfo;

import java.io.Serializable;

public class RoomUserBean implements Serializable {


    /**
     * banned : 0
     * favorite : 0
     * pit : 0
     * voice : 0
     * shutup : 0
     */

    private int banned;
    private int favorite;
    private int pit;
    private int voice;//1开 2关 麦克风
    private int shutup;
    private RankInfo rank_info;
    private int show_cat;
    private int guide;
    private int mixer;
    private int apply_wait;//是否排麦中，1是 0否
    private int role;
    private String rank_icon;
    private String nobility_icon;
    private String nickname;
    private String user_id;
    private int user_is_new;
    private int apply_wait_type;//1申请上老板位2申请上1-7号麦

    public int getApply_wait_type() {
        return apply_wait_type;
    }

    public void setApply_wait_type(int apply_wait_type) {
        this.apply_wait_type = apply_wait_type;
    }

    public int getUser_is_new() {
        return user_is_new;
    }

    public void setUser_is_new(int user_is_new) {
        this.user_is_new = user_is_new;
    }

    private String ball;//a1,b1,c1l

    private String room_id;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRank_icon() {
        return rank_icon;
    }

    public void setRank_icon(String rank_icon) {
        this.rank_icon = rank_icon;
    }

    public String getNobility_icon() {
        return nobility_icon;
    }

    public void setNobility_icon(String nobility_icon) {
        this.nobility_icon = nobility_icon;
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

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public int getApply_wait() {
        return apply_wait;
    }

    public void setApply_wait(int apply_wait) {
        this.apply_wait = apply_wait;
    }

    public int getMixer() {
        return mixer;
    }

    public void setMixer(int mixer) {
        this.mixer = mixer;
    }

    public int getGuide() {
        return guide;
    }

    public void setGuide(int guide) {
        this.guide = guide;
    }

    public int getShow_cat() {
        return show_cat;
    }

    public void setShow_cat(int show_cat) {
        this.show_cat = show_cat;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getPit() {
        return pit;
    }

    public void setPit(int pit) {
        this.pit = pit;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getShutup() {
        return shutup;
    }

    public void setShutup(int shutup) {
        this.shutup = shutup;
    }
}
