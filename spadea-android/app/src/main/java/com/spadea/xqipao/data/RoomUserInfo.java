package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

import java.util.List;

public class
RoomUserInfo {


    /**
     * user_id : 6
     * user_code : 772835
     * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/6/20200107152724_1578382044529394.jpg
     * nickname : 兔兔咯啦
     * sex : 1
     * room_id : 13
     * room_id_current : 1
     * user_photo : []
     * signature :
     * constellation :
     * profession :
     * last_online_time : 1579166809
     * offline_time : 1579167990
     * emchat_username : 2B61813BC18A8D06152F9BCFDAE2411A
     * follow : 0
     * follow_count : 10
     * fans_count : 0
     * age : 1
     * auth : 0
     * gift_list : [{"number":"1","gift_id":"77","name":"木荧","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/A6d27YjxSh.png","price":"666666"},{"number":"3","gift_id":"78","name":"摘星揽月","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/MtSk6yyRG7.png","price":"334400"},{"number":"1","gift_id":"95","name":"猫女孩","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/sKXtZ7CReM.png","price":"88888"},{"number":"1","gift_id":"98","name":"面具","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/MC5BhTnif8.png","price":"5200"},{"number":"4","gift_id":"100","name":"爱心","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/rYAXnDMtbT.png","price":"1999"},{"number":"3","gift_id":"107","name":"羽毛","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/FzCDQYGG5m.png","price":"10"},{"number":"2","gift_id":"108","name":"加油打气","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/ZBQS5RF8Kb.png","price":"6666"},{"number":"23","gift_id":"112","name":"天使翅膀","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/2Nyr8GXEts.png","price":"2999"},{"number":"2","gift_id":"120","name":"项链","picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/mt5aiKzdNn.png","price":"3999"},{"number":"1","gift_id":"131","name":"情侣","picture":"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/CiHDf4kFZ7.png","price":"131400"},{"number":"3","gift_id":"136","name":"圣诞袜","picture":"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/mXYi5Xai6a.png","price":"521"},{"number":"3353","gift_id":"137","name":"圣诞帽","picture":"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/4zDpAr6N7N.png","price":"1314"}]
     * city :
     * online_text : 秒前
     * label_count : 4
     * rank_info : {"rank_id":54,"rank_name":"王冠","nobility_id":5,"nobility_name":"王爵","picture":"http://yutangyuyin1.oss-cn-hangzhou.aliyuncs.com/admin_images/5e169805bd7f5.png"}
     * room_info : {"room_id":1,"room_code":1250813,"room_name":"xf","role":3,"shutup":0,"pit_number":2,"voice":0,"state":2}
     */

    private String user_id;
    private String user_code;
    private String head_picture;
    private String nickname;
    private String sex;
    private String room_id;
    private String room_id_current;
    private String signature;
    private String constellation;
    private String profession;
    private String last_online_time;
    private String offline_time;
    private String emchat_username;
    private int follow;
    private String follow_count;
    private int fans_count;
    private int age;
    private int auth;
    private int role;
    private String city;
    private String online_text;
    private int label_count;
    private RankInfo rank_info;
    private RoomInfoBean room_info;

    private List<GiftListBean> gift_list;

    private int only_friend;

    private int user_is_new;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUser_is_new() {
        return user_is_new;
    }

    public void setUser_is_new(int user_is_new) {
        this.user_is_new = user_is_new;
    }

    public int getOnly_friend() {
        return only_friend;
    }

    public void setOnly_friend(int only_friend) {
        this.only_friend = only_friend;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_id_current() {
        return room_id_current;
    }

    public void setRoom_id_current(String room_id_current) {
        this.room_id_current = room_id_current;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLast_online_time() {
        return last_online_time;
    }

    public void setLast_online_time(String last_online_time) {
        this.last_online_time = last_online_time;
    }

    public String getOffline_time() {
        return offline_time;
    }

    public void setOffline_time(String offline_time) {
        this.offline_time = offline_time;
    }

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public String getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(String follow_count) {
        this.follow_count = follow_count;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOnline_text() {
        return online_text;
    }

    public void setOnline_text(String online_text) {
        this.online_text = online_text;
    }

    public int getLabel_count() {
        return label_count;
    }

    public void setLabel_count(int label_count) {
        this.label_count = label_count;
    }



    public RoomInfoBean getRoom_info() {
        return room_info;
    }

    public void setRoom_info(RoomInfoBean room_info) {
        this.room_info = room_info;
    }



    public List<GiftListBean> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<GiftListBean> gift_list) {
        this.gift_list = gift_list;
    }



    public static class RoomInfoBean {
        /**
         * room_id : 1
         * room_code : 1250813
         * room_name : xf
         * role : 3
         * shutup : 0
         * pit_number : 2
         * voice : 0
         * state : 2
         */

        private int room_id;
        private int room_code;
        private String room_name;
        private int role;
        private int shutup;
        private int pit_number;
        private int voice;
        private int state;
        private int banned;


        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public int getRoom_code() {
            return room_code;
        }

        public void setRoom_code(int room_code) {
            this.room_code = room_code;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getShutup() {
            return shutup;
        }

        public void setShutup(int shutup) {
            this.shutup = shutup;
        }

        public int getPit_number() {
            return pit_number;
        }

        public void setPit_number(int pit_number) {
            this.pit_number = pit_number;
        }

        public int getVoice() {
            return voice;
        }

        public void setVoice(int voice) {
            this.voice = voice;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    public static class GiftListBean {
        /**
         * number : 1
         * gift_id : 77
         * name : 木荧
         * picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/A6d27YjxSh.png
         * price : 666666
         */

        private String number;
        private String gift_id;
        private String name;
        private String picture;
        private String price;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getGift_id() {
            return gift_id;
        }

        public void setGift_id(String gift_id) {
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
