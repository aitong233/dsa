package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

import java.util.List;

public class RoomUserInfoModel {


    /**
     * user_id :
     * head_picture :
     * nickname :
     * sex :
     * rank_id :
     * room_id :
     * room_id_current :
     */

    private String user_id;
    private String head_picture;
    private String nickname;
    private String sex;
    private String rank_id;
    private String room_id;
    private String room_id_current;
    private String follow;
    private String user_code;
    private String signature;
    private String constellation;
    private String profession;
    private String follow_count;
    private String fans_count;
    private String age;
    private String auth;
    private String city;
    private List<String> user_photo;
    private List<UserInfoDataModel.GiftListBean> gift_list;
    private String online_text;
    private String emchat_username;
    private RankInfo rank_info;

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(List<String> user_photo) {
        this.user_photo = user_photo;
    }

    public List<UserInfoDataModel.GiftListBean> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<UserInfoDataModel.GiftListBean> gift_list) {
        this.gift_list = gift_list;
    }

    public String getOnline_text() {
        return online_text;
    }

    public void setOnline_text(String online_text) {
        this.online_text = online_text;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
