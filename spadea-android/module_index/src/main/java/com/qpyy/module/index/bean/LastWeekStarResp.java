package com.qpyy.module.index.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.bean
 * 创建人 王欧
 * 创建时间 2020/6/30 10:03 AM
 * 描述 describe
 */
public class LastWeekStarResp {

    /**
     * last_star : {"user_id":"","nickname":"","head_picture":"","total_price":"","level":"","nobility_id":"","nobility_icon":"","level_icon":""}
     * top_rich_three : {"user_id":"","nickname":"","head_picture":"","total_price":"","level":"","nobility_id":"","follow":"","gift_picture":"","nobility_icon":"","level_icon":"","gift_name":""}
     */

    private LastStarBean last_star;
    private List<TopRichThreeBean> top_rich_three;

    public LastStarBean getLast_star() {
        return last_star;
    }

    public void setLast_star(LastStarBean last_star) {
        this.last_star = last_star;
    }

    public List<TopRichThreeBean> getTop_rich_three() {
        return top_rich_three;
    }

    public void setTop_rich_three(List<TopRichThreeBean> top_rich_three) {
        this.top_rich_three = top_rich_three;
    }

    public static class LastStarBean {
        /**
         * user_id :
         * nickname :
         * head_picture :
         * total_price :
         * level :
         * nobility_id :
         * nobility_icon :
         * level_icon :
         */

        private String user_id;
        private String nickname;
        private String head_picture;
        private String total_price;
        private String level;
        private String nobility_id;
        private String nobility_icon;
        private String level_icon;
        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNobility_id() {
            return nobility_id;
        }

        public void setNobility_id(String nobility_id) {
            this.nobility_id = nobility_id;
        }

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
    }

    public static class TopRichThreeBean {
        /**
         * user_id :
         * nickname :
         * head_picture :
         * total_price :
         * level :
         * nobility_id :
         * follow :
         * gift_picture :
         * nobility_icon :
         * level_icon :
         * gift_name :
         */

        private String user_id;
        private String nickname;
        private String head_picture;
        private String total_price;
        private String level;
        private String nobility_id;
        private String follow;
        private String gift_picture;
        private String nobility_icon;
        private String level_icon;
        private String gift_name;
        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNobility_id() {
            return nobility_id;
        }

        public void setNobility_id(String nobility_id) {
            this.nobility_id = nobility_id;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getGift_picture() {
            return gift_picture;
        }

        public void setGift_picture(String gift_picture) {
            this.gift_picture = gift_picture;
        }

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

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }
    }
}
