package com.qpyy.room.bean;

import com.qpyy.libcommon.bean.RankInfo;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/7/25 14:38
 * 描述 describe
 */
public class WealthRankingResp {
    private MyBean my;
    private List<ListsBean> lists;

    public MyBean getMy() {
        return my;
    }

    public void setMy(MyBean my) {
        this.my = my;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class MyBean {
        /**
         * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/577547/20200331141523_1585635321420.png
         * nickname : 687592
         * user_id : 577547
         * level : 55
         * number : 0
         * rank : -1
         * diff : 52474
         * rank_info : {"rank_id":"55","rank_name":1000,"nobility_id":0,"nobility_name":"","picture":""}
         */

        private String head_picture;
        private String nickname;
        private String user_id;
        private String level;
        private String number;
        private int rank;
        private String diff;
        private String sex;
        private String number_format;

        public String getNumber_format() {
            return number_format;
        }

        public void setNumber_format(String number_format) {
            this.number_format = number_format;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        private RankInfo rank_info;
        private String nobility_icon;
        private String level_icon;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getDiff() {
            return diff;
        }

        public void setDiff(String diff) {
            this.diff = diff;
        }

        public RankInfo getRank_info() {
            return rank_info;
        }

        public void setRank_info(RankInfo rank_info) {
            this.rank_info = rank_info;
        }
    }

    public static class ListsBean {
        /**
         * user_id : 642649
         * number : 43280001
         * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/642649/20200601132728_1590989246548.jpeg
         * nickname : 109179
         * level : 4
         * sex : 1
         * nobility_icon : http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5e71d1ef847ba.png
         * rank : 1
         * rank_info : {"rank_id":"4","rank_name":4,"nobility_id":6,"nobility_name":"帝皇","picture":""}
         */

        private String user_id;
        private String number;
        private String head_picture;
        private String nickname;
        private String level;
        private String sex;
        private String nobility_icon;
        private String level_icon;
        private int rank;

        private RankInfo rank_info;
        private String number_format;

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        private String user_code;

        public String getNumber_format() {
            return number_format;
        }

        public void setNumber_format(String number_format) {
            this.number_format = number_format;
        }

        public String getLevel_icon() {
            return level_icon;
        }

        public void setLevel_icon(String level_icon) {
            this.level_icon = level_icon;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNobility_icon() {
            return nobility_icon;
        }

        public void setNobility_icon(String nobility_icon) {
            this.nobility_icon = nobility_icon;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public RankInfo getRank_info() {
            return rank_info;
        }

        public void setRank_info(RankInfo rank_info) {
            this.rank_info = rank_info;
        }
    }
}
