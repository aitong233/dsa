package com.spadea.xqipao.data;

import java.io.Serializable;
import java.util.List;

public class RoomDetailModel implements Serializable {


    /**
     * parties : {"house_owner":0,"manager":"0","head_picture":"mixed","nickname":"mixed","pit":"7"}
     * house_user : {"user_id":"546000","playing":"你点我唱，满足你所有想象","greeting":"风风火火的来了","room_id":"0","label_id":"1","label_name":"K歌","head_picture":"mock","nickname":"23123","sex":"2","xin_dong":100,"voice":1,"popularity":"1","contribute":1000}
     * house_pit : [{"user_id":"546003","pit_number":"5","voice":"1","nickname":"mixed","head_picture":"mixed","sex":"mixed","xin_dong":100},{"user_id":"546002","pit_number":"7","voice":"0","nickname":"mixed","head_picture":"mixed","sex":"mixed","xin_dong":100}]
     */

    private PartiesBean parties;
    private HouseUserBean house_user;
    private List<HousePitBean> house_pit;

    public PartiesBean getParties() {
        return parties;
    }

    public void setParties(PartiesBean parties) {
        this.parties = parties;
    }

    public HouseUserBean getHouse_user() {
        return house_user;
    }

    public void setHouse_user(HouseUserBean house_user) {
        this.house_user = house_user;
    }

    public List<HousePitBean> getHouse_pit() {
        return house_pit;
    }

    public void setHouse_pit(List<HousePitBean> house_pit) {
        this.house_pit = house_pit;
    }

    public static class PartiesBean implements Serializable {
        /**
         * house_owner : 0
         * manager : 0
         * head_picture : mixed
         * nickname : mixed
         * pit : 7
         */

        private int house_owner;
        private int manager;
        private String head_picture;
        private String nickname;
        private int pit;
        private int favorite;
        private int banned;
        private int voice;
        private int shutup;

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public int getVoice() {
            return voice;
        }

        public void setVoice(int voice) {
            this.voice = voice;
        }

        public int getShutup() {
            return shutup;
        }

        public void setShutup(int shutup) {
            this.shutup = shutup;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getHouse_owner() {
            return house_owner;
        }

        public void setHouse_owner(int house_owner) {
            this.house_owner = house_owner;
        }

        public int getManager() {
            return manager;
        }

        public void setManager(int manager) {
            this.manager = manager;
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

        public int getPit() {
            return pit;
        }

        public void setPit(int pit) {
            this.pit = pit;
        }
    }

    public static class HouseUserBean implements Serializable {
        /**
         * user_id : 546000
         * playing : 你点我唱，满足你所有想象
         * greeting : 风风火火的来了
         * room_id : 0
         * label_id : 1
         * label_name : K歌
         * head_picture : mock
         * nickname : 23123
         * sex : 2
         * xin_dong : 100
         * voice : 1
         * popularity : 1
         * contribute : 1000
         */

        private String id;
        private String user_id;
        private String playing;
        private String greeting;
        private String room_id;
        private String label_id;
        private String label_name;
        private String head_picture;
        private String nickname;
        private int sex;
        private int xin_dong;
        private int voice;
        private int popularity;
        private String contribute;
        private String contribution;
        private int wheat;
        private int even_wheat;
        private String chatrooms;
        private String dress_picture;
        private String dress_name;
        private int is_password;
        private int cardiac;
        private String room_name;
        private String cover_picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public int getCardiac() {
            return cardiac;
        }

        public void setCardiac(int cardiac) {
            this.cardiac = cardiac;
        }

        public int getIs_password() {
            return is_password;
        }

        public void setIs_password(int is_password) {
            this.is_password = is_password;
        }

        public String getContribution() {
            return contribution;
        }

        public void setContribution(String contribution) {
            this.contribution = contribution;
        }

        public String getDress_picture() {
            return dress_picture;
        }

        public void setDress_picture(String dress_picture) {
            this.dress_picture = dress_picture;
        }

        public String getDress_name() {
            return dress_name;
        }

        public void setDress_name(String dress_name) {
            this.dress_name = dress_name;
        }

        public String getChatrooms() {
            return chatrooms;
        }

        public void setChatrooms(String chatrooms) {
            this.chatrooms = chatrooms;
        }

        public int getEven_wheat() {
            return even_wheat;
        }

        public void setEven_wheat(int even_wheat) {
            this.even_wheat = even_wheat;
        }

        public int getWheat() {
            return wheat;
        }

        public void setWheat(int wheat) {
            this.wheat = wheat;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPlaying() {
            return playing;
        }

        public void setPlaying(String playing) {
            this.playing = playing;
        }

        public String getGreeting() {
            return greeting;
        }

        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getLabel_id() {
            return label_id;
        }

        public void setLabel_id(String label_id) {
            this.label_id = label_id;
        }

        public String getLabel_name() {
            return label_name;
        }

        public void setLabel_name(String label_name) {
            this.label_name = label_name;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getXin_dong() {
            return xin_dong;
        }

        public void setXin_dong(int xin_dong) {
            this.xin_dong = xin_dong;
        }

        public int getVoice() {
            return voice;
        }

        public void setVoice(int voice) {
            this.voice = voice;
        }

        public int getPopularity() {
            return popularity;
        }

        public void setPopularity(int popularity) {
            this.popularity = popularity;
        }

        public String getContribute() {
            return contribute;
        }

        public void setContribute(String contribute) {
            this.contribute = contribute;
        }
    }

    public static class HousePitBean implements Serializable {
        /**
         * user_id : 546003
         * pit_number : 5
         * voice : 1
         * nickname : mixed
         * head_picture : mixed
         * sex : mixed
         * xin_dong : 100
         */

        private String user_id;
        private int pit_number = Integer.MAX_VALUE;
        private int voice;
        private String nickname;
        private String head_picture;
        private int sex;
        private String xin_dong;
        private int age;
        private String emchat_username;
        private String rank_id;
        private String nobility_name;
        private boolean isChecked;
        private String dress_picture;
        private String dress_name;
        private int banned;
        private int shutup;

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public int getShutup() {
            return shutup;
        }

        public void setShutup(int shutup) {
            this.shutup = shutup;
        }

        public String getDress_picture() {
            return dress_picture;
        }

        public void setDress_picture(String dress_picture) {
            this.dress_picture = dress_picture;
        }

        public String getDress_name() {
            return dress_name;
        }

        public void setDress_name(String dress_name) {
            this.dress_name = dress_name;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getEmchat_username() {
            return emchat_username;
        }

        public void setEmchat_username(String emchat_username) {
            this.emchat_username = emchat_username;
        }

        public String getRank_id() {
            return rank_id;
        }

        public void setRank_id(String rank_id) {
            this.rank_id = rank_id;
        }

        public String getNobility_name() {
            return nobility_name;
        }

        public void setNobility_name(String nobility_name) {
            this.nobility_name = nobility_name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getXin_dong() {
            return xin_dong;
        }

        public void setXin_dong(String xin_dong) {
            this.xin_dong = xin_dong;
        }
    }
}
