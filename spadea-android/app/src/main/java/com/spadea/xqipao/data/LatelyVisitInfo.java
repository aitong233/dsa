package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

public class LatelyVisitInfo {


    /**
     * add_time : 9天前
     * user_id : 18
     * visit_user : 18
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2020-01-07/BD18AF80-4372-4302-ABC6-0F68B5CC34FC.png
     * sex : 1
     * nickname : 你的人生
     * age : 11
     * rank_id : 33
     * signature :
     */

    private String add_time;
    private String user_id;
    private String visit_user;
    private String head_picture;
    private String sex;
    private String nickname;
    private int age;
    private String rank_id;
    private String signature;
    private RankInfo rank_info;


    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVisit_user() {
        return visit_user;
    }

    public void setVisit_user(String visit_user) {
        this.visit_user = visit_user;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
