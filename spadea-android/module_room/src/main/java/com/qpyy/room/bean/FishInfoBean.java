package com.qpyy.room.bean;

import java.util.List;


public class FishInfoBean {


    /**
     * user_balance :
     * sprite_list : [{"lucky_bag_one_cost":"","lucky_bag_ten_cost":"","lucky_bag_hundred_cost":"","sprite_active":"","sprite_info":{"lucky_bag_type":"","current_level":"","current_exp":"","next_level_exp":"","gift_id":"","name":"","picture":"","special":""}}]
     */

    private String user_balance;
    private List<SpriteListBean> sprite_list;

    public String getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(String user_balance) {
        this.user_balance = user_balance;
    }

    public List<SpriteListBean> getSprite_list() {
        return sprite_list;
    }

    public void setSprite_list(List<SpriteListBean> sprite_list) {
        this.sprite_list = sprite_list;
    }

    public static class SpriteListBean {
        /**
         * lucky_bag_one_cost :
         * lucky_bag_ten_cost :
         * lucky_bag_hundred_cost :
         * sprite_active :
         * sprite_info : {"lucky_bag_type":"","current_level":"","current_exp":"","next_level_exp":"","gift_id":"","name":"","picture":"","special":""}
         */

        private String lucky_bag_one_cost;
        private String lucky_bag_ten_cost;
        private String lucky_bag_hundred_cost;
        private String sprite_active;
        private LuckGiftBean.SpriteInfoBean sprite_info;

        public String getLucky_bag_one_cost() {
            return lucky_bag_one_cost;
        }

        public void setLucky_bag_one_cost(String lucky_bag_one_cost) {
            this.lucky_bag_one_cost = lucky_bag_one_cost;
        }

        public String getLucky_bag_ten_cost() {
            return lucky_bag_ten_cost;
        }

        public void setLucky_bag_ten_cost(String lucky_bag_ten_cost) {
            this.lucky_bag_ten_cost = lucky_bag_ten_cost;
        }

        public String getLucky_bag_hundred_cost() {
            return lucky_bag_hundred_cost;
        }

        public void setLucky_bag_hundred_cost(String lucky_bag_hundred_cost) {
            this.lucky_bag_hundred_cost = lucky_bag_hundred_cost;
        }

        public boolean getSprite_active() {
            return "1".equals(sprite_active);
        }

        public void setSprite_active(String sprite_active) {
            this.sprite_active = sprite_active;
        }

        public LuckGiftBean.SpriteInfoBean getSprite_info() {
            return sprite_info;
        }

        public void setSprite_info(LuckGiftBean.SpriteInfoBean sprite_info) {
            this.sprite_info = sprite_info;
        }

    }

}
