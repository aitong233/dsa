package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class OnlineModel {


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
    private int sex;
    private int role;
    private RankInfo rank_info;
    private int user_is_new;

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
