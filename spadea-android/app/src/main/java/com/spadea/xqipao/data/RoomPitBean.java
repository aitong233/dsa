package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class RoomPitBean {


    /**
     * id : 1549
     * room_id : 173
     * user_id : 559397
     * pit_number : 1
     * voice : 0
     * shutup : 0
     * state : 2
     * nickname : 麒麟🌺
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/559397/20200305123221_158338274166572.jpeg
     * sex : 2
     * emchat_username : ol7xe0Q5CfoV2lMgdEJ_B2K9wcrk
     * rank_id : 2
     * nobility : 0
     * xin_dong : 10
     * banned : 0
     * dress_picture :
     */

    private String id;
    private String room_id;
    private String user_id;
    private String pit_number;
    private String voice;
    private String shutup;
    private String state;
    private String nickname;
    private String head_picture;
    private String sex;
    private String emchat_username;
    private String rank_id;
    private String nobility;
    private String xin_dong;
    private int banned;
    private String dress_picture;
    private RankInfo rank_info;
    private int count_down;

    public int getCount_down() {
        return count_down;
    }

    public void setCount_down(int count_down) {
        this.count_down = count_down;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getShutup() {
        return shutup;
    }

    public void setShutup(String shutup) {
        this.shutup = shutup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
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

    public String getXin_dong() {
        return xin_dong;
    }

    public void setXin_dong(String xin_dong) {
        this.xin_dong = xin_dong;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public String getDress_picture() {
        return dress_picture;
    }

    public void setDress_picture(String dress_picture) {
        this.dress_picture = dress_picture;
    }
}
