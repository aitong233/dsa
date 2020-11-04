package com.yutang.game.fudai.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LuckGiftBean {

    /**
     * user_balance : 986
     * sprite_info : {"lucky_bag_type":1,"current_level":2,"current_exp":74,"next_level_exp":350,"gift_id":251,"name":"璀璨星河","picture":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4b46781.png","special":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4ea1aed.svga"}
     * sprite_active : 1
     * prize_info : [{"gift_id":253,"name":"鼓掌","picture":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f92aa94c.png","price":10,"special":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f95d3c1b.png","count":1,"is_sprite":0,"gift_quality":"白色"}]
     */

    private String user_balance;
    private SpriteInfoBean sprite_info;
    private int sprite_active;
    private ArrayList<PrizeInfoBean> prize_info;

    public String getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(String user_balance) {
        this.user_balance = user_balance;
    }

    public SpriteInfoBean getSprite_info() {
        return sprite_info;
    }

    public void setSprite_info(SpriteInfoBean sprite_info) {
        this.sprite_info = sprite_info;
    }

    public int getSprite_active() {
        return sprite_active;
    }

    public void setSprite_active(int sprite_active) {
        this.sprite_active = sprite_active;
    }

    public ArrayList<PrizeInfoBean> getPrize_info() {
        return prize_info;
    }

    public void setPrize_info(ArrayList<PrizeInfoBean> prize_info) {
        this.prize_info = prize_info;
    }

    public static class SpriteInfoBean {
        /**
         * lucky_bag_type : 1
         * current_level : 2
         * current_exp : 74
         * next_level_exp : 350
         * gift_id : 251
         * name : 璀璨星河
         * picture : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4b46781.png
         * special : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4ea1aed.svga
         */

        private int lucky_bag_type;
        private int current_level;
        private int current_exp;
        private int next_level_exp;
        private int gift_id;
        private String name;
        private String picture;
        private String special;

        public int getLucky_bag_type() {
            return lucky_bag_type;
        }

        public void setLucky_bag_type(int lucky_bag_type) {
            this.lucky_bag_type = lucky_bag_type;
        }

        public int getCurrent_level() {
            return current_level;
        }

        public void setCurrent_level(int current_level) {
            this.current_level = current_level;
        }

        public int getCurrent_exp() {
            return current_exp;
        }

        public void setCurrent_exp(int current_exp) {
            this.current_exp = current_exp;
        }

        public int getNext_level_exp() {
            return next_level_exp;
        }

        public void setNext_level_exp(int next_level_exp) {
            this.next_level_exp = next_level_exp;
        }

        public int getGift_id() {
            return gift_id;
        }

        public void setGift_id(int gift_id) {
            this.gift_id = gift_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }

    public static class PrizeInfoBean implements Serializable {
        /**
         * gift_id : 253
         * name : 鼓掌
         * picture : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f92aa94c.png
         * price : 10
         * special : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f95d3c1b.png
         * count : 1
         * is_sprite : 0
         * gift_quality : 白色
         */

        private int gift_id;
        private String name;
        private String picture;
        private int price;
        private String special;
        private int count;
        private int is_sprite;
        private String gift_quality;

        public int getGift_id() {
            return gift_id;
        }

        public void setGift_id(int gift_id) {
            this.gift_id = gift_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean getIs_sprite() {
            return is_sprite == 1;
        }

        public void setIs_sprite(int is_sprite) {
            this.is_sprite = is_sprite;
        }

        public String getGift_quality() {
            return gift_quality;
        }

        public void setGift_quality(String gift_quality) {
            this.gift_quality = gift_quality;
        }
    }
}
