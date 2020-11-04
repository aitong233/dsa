package com.spadea.xqipao.data;

import java.util.List;

public class FullGiftBean {


    /**
     * gift : 木荧
     * special : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/QRE63PZ6tk.svga
     * room_id : 795
     * to_user : [{"nickname":"244813"}]
     * number : 1
     * room_name : 鱼糖测试2
     * room_info : {"user_id":547969,"popularity":33373,"cover_picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547969/20191220210844_1576847324376449.jpg","chatrooms":"100723521028097","is_password":0,"id":795,"head_picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2019-11-30/C5D82F60-9B2B-4290-B742-FFE7AA550C49.png","cardiac":2}
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547969/20191220181901_157683714173951.jpg
     * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/A6d27YjxSh.png
     * from_user : [{"nickname":"810285"}]
     */

    private String gift;
    private String special;
    private String room_id;
    private String number;
    private String room_name;
    private RoomInfoBean room_info;
    private String head_picture;
    private String picture;
    private List<ToUserBean> to_user;
    private List<FromUserBean> from_user;

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public RoomInfoBean getRoom_info() {
        return room_info;
    }

    public void setRoom_info(RoomInfoBean room_info) {
        this.room_info = room_info;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ToUserBean> getTo_user() {
        return to_user;
    }

    public void setTo_user(List<ToUserBean> to_user) {
        this.to_user = to_user;
    }

    public List<FromUserBean> getFrom_user() {
        return from_user;
    }

    public void setFrom_user(List<FromUserBean> from_user) {
        this.from_user = from_user;
    }

    public static class RoomInfoBean {
        /**
         * user_id : 547969
         * popularity : 33373
         * cover_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/547969/20191220210844_1576847324376449.jpg
         * chatrooms : 100723521028097
         * is_password : 0
         * id : 795
         * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2019-11-30/C5D82F60-9B2B-4290-B742-FFE7AA550C49.png
         * cardiac : 2
         */

        private int user_id;
        private int popularity;
        private String cover_picture;
        private String chatrooms;
        private int is_password;
        private int id;
        private String head_picture;
        private int cardiac;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPopularity() {
            return popularity;
        }

        public void setPopularity(int popularity) {
            this.popularity = popularity;
        }

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
        }

        public String getChatrooms() {
            return chatrooms;
        }

        public void setChatrooms(String chatrooms) {
            this.chatrooms = chatrooms;
        }

        public int getIs_password() {
            return is_password;
        }

        public void setIs_password(int is_password) {
            this.is_password = is_password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHead_picture() {
            return head_picture;
        }

        public void setHead_picture(String head_picture) {
            this.head_picture = head_picture;
        }

        public int getCardiac() {
            return cardiac;
        }

        public void setCardiac(int cardiac) {
            this.cardiac = cardiac;
        }
    }

    public static class ToUserBean {
        /**
         * nickname : 244813
         */

        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class FromUserBean {
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
