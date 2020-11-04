package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class FriendModel {


    /**
     * friend_id :
     * nickname :
     * head_picture :
     * signature :
     */

    private String friend_id;
    private String nickname;
    private String head_picture;
    private String signature;
    private String followed_user;
    private String age;
    private String sex;
    private String user_id;
    private RankInfo rank_info;

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

    public String getFollowed_user() {
        return followed_user;
    }

    public void setFollowed_user(String followed_user) {
        this.followed_user = followed_user;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
