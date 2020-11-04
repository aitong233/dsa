package com.spadea.xqipao.data.socket;

public class SpriteInfo {

    /**
     * sprite_info : {"special":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4ea1aed.svga","lucky_bag_type":3,"next_level_exp":500,"current_level":2,"gift_id":251,"name":"璀璨星河","current_exp":386,"picture":"http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4b46781.png"}
     * action : 5
     */

    private SpriteInfoBean sprite_info;
    private int action;

    public SpriteInfoBean getSprite_info() {
        return sprite_info;
    }

    public void setSprite_info(SpriteInfoBean sprite_info) {
        this.sprite_info = sprite_info;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public static class SpriteInfoBean {
        /**
         * special : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4ea1aed.svga
         * lucky_bag_type : 3
         * next_level_exp : 500
         * current_level : 2
         * gift_id : 251
         * name : 璀璨星河
         * current_exp : 386
         * picture : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182f4b46781.png
         */

        private String special;
        private int lucky_bag_type;
        private int next_level_exp;
        private int current_level;
        private int gift_id;
        private String name;
        private int current_exp;
        private String picture;

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public int getLucky_bag_type() {
            return lucky_bag_type;
        }

        public void setLucky_bag_type(int lucky_bag_type) {
            this.lucky_bag_type = lucky_bag_type;
        }

        public int getNext_level_exp() {
            return next_level_exp;
        }

        public void setNext_level_exp(int next_level_exp) {
            this.next_level_exp = next_level_exp;
        }

        public int getCurrent_level() {
            return current_level;
        }

        public void setCurrent_level(int current_level) {
            this.current_level = current_level;
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

        public int getCurrent_exp() {
            return current_exp;
        }

        public void setCurrent_exp(int current_exp) {
            this.current_exp = current_exp;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }


}
