package com.qpyy.libcommon.bean;

import java.util.List;

public class RoomGiveGiftModel {


    /**
     * room_id : 3
     * gift_list : [{"user_id":"547177","nickname_from":"titititi","nickname_to":"佳人有约","gift_name":"比心","picture":"礼物图片url","special":"礼物特效url","number":"1"}]
     * cardiac_list : [{"rough_number":"当前麦位心动值","room_id":"3","pit_number":"1"},{"rough_number":"0","room_id":"3","pit_number":"2"},{"rough_number":"0","room_id":"3","pit_number":"3"},{"rough_number":"0","room_id":"3","pit_number":"4"},{"rough_number":"0","room_id":"3","pit_number":"5"},{"rough_number":"0","room_id":"3","pit_number":"6"},{"rough_number":"0","room_id":"3","pit_number":"7"},{"rough_number":"0","room_id":"3","pit_number":"8"},{"rough_number":"0","room_id":"3","pit_number":"9"}]
     * contribution : 5430
     */

    private String room_id;
    private String contribution;
    private List<GiftListBean> gift_list;
    private List<CardiacListBean> cardiac_list;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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
         * user_id : 547177
         * nickname_from : titititi
         * nickname_to : 佳人有约
         * gift_name : 比心
         * picture : 礼物图片url
         * special : 礼物特效url
         * number : 1
         */

        private String user_id;
        private String nickname_from;
        private String nickname_to;
        private String gift_name;
        private String picture;
        private String special;
        private String number;
        private String head_picture;

        public String getHead_picture() {
            return head_picture;
        }

        public void setHead_picture(String head_picture) {
            this.head_picture = head_picture;
        }

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
         * rough_number : 当前麦位心动值
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
