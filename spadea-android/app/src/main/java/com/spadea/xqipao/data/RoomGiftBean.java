package com.spadea.xqipao.data;

import java.util.List;

public class RoomGiftBean {

    /**
     * from_user : [{"nickname":"因为一个人爱上一座城"}]
     * to_user : [{"nickname":"810285"}]
     * gift : 羽毛
     * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/FzCDQYGG5m.png
     * special : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/aNEQkjT2cc.png
     * number : 1
     */

    private String gift;
    private String picture;
    private String special;
    private String number;
    private List<FromUserBean> from_user;
    private List<ToUserBean> to_user;

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
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

    public List<FromUserBean> getFrom_user() {
        return from_user;
    }

    public void setFrom_user(List<FromUserBean> from_user) {
        this.from_user = from_user;
    }

    public List<ToUserBean> getTo_user() {
        return to_user;
    }

    public void setTo_user(List<ToUserBean> to_user) {
        this.to_user = to_user;
    }

    public static class FromUserBean {
        /**
         * nickname : 因为一个人爱上一座城
         */

        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class ToUserBean {
        /**
         * nickname : 810285
         */

        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
