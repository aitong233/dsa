package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class SearchUserInfo {


    /**
     * user_id :
     * nickname :
     * head_picture :
     * sex :
     */

    private String user_id;
    private String nickname;
    private String head_picture;
    private String sex;
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
}
