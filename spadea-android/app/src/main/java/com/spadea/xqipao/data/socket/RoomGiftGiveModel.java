package com.spadea.xqipao.data.socket;

import java.util.List;

public class RoomGiftGiveModel {


    /**
     * room_id : 3
     * gift_list : [{"user_id":"2","nickname_from":"因为一个人爱上一座城","nickname_to":"因为一个人","gift_name":"初恋","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/ffXwrGW6Bc.png","special":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/BkWinjbkfT.svga","number":"1"},{"user_id":"30","nickname_from":"因为一个人爱上一座城","nickname_to":"因为一个人爱上一座城","gift_name":"初恋","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/ffXwrGW6Bc.png","special":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/BkWinjbkfT.svga","number":"1"}]
     * popularity : 33450
     * cardiac_list : [{"rough_number":"334400","room_id":"3","pit_number":"1"},{"rough_number":"0","room_id":"3","pit_number":"2"},{"rough_number":"0","room_id":"3","pit_number":"3"},{"rough_number":"0","room_id":"3","pit_number":"4"},{"rough_number":"0","room_id":"3","pit_number":"5"},{"rough_number":"0","room_id":"3","pit_number":"6"},{"rough_number":"0","room_id":"3","pit_number":"7"},{"rough_number":"0","room_id":"3","pit_number":"8"},{"rough_number":"334400","room_id":"3","pit_number":"9"}]
     * contribution : 85857557
     */

    private String room_id;
    private int popularity;
    private int show_cat;
    private String user_id;
    private String contribution;
    private List<GiftListBean> gift_list;
    private List<CardiacListBean> cardiac_list;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getShow_cat() {
        return show_cat;
    }

    public void setShow_cat(int show_cat) {
        this.show_cat = show_cat;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public List<GiftListBean> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<GiftListBean> gift_list) {
        this.gift_list = gift_list;
    }

    public List<CardiacListBean> getCardiac_list() {
        return cardiac_list;
    }

    public void setCardiac_list(List<CardiacListBean> cardiac_list) {
        this.cardiac_list = cardiac_list;
    }

    public static class GiftListBean {
        /**
         * user_id : 2
         * nickname_from : 因为一个人爱上一座城
         * nickname_to : 因为一个人
         * gift_name : 初恋
         * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/ffXwrGW6Bc.png
         * special : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/BkWinjbkfT.svga
         * number : 1
         */

        private String user_id;
        private String nickname_from;
        private String nickname_to;
        private String gift_name;
        private String picture;
        private String special;
        private String number;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname_from() {
            return nickname_from;
        }

        public void setNickname_from(String nickname_from) {
            this.nickname_from = nickname_from;
        }

        public String getNickname_to() {
            return nickname_to;
        }

        public void setNickname_to(String nickname_to) {
            this.nickname_to = nickname_to;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    public static class CardiacListBean {
        /**
         * rough_number : 334400
         * room_id : 3
         * pit_number : 1
         */

        private String rough_number;
        private String room_id;
        private String pit_number;

        public String getRough_number() {
            return rough_number;
        }

        public void setRough_number(String rough_number) {
            this.rough_number = rough_number;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getPit_number() {
            return pit_number;
        }

        public void setPit_number(String pit_number) {
            this.pit_number = pit_number;
        }
    }
}
