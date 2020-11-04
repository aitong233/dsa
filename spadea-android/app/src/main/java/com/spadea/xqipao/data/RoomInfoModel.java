package com.spadea.xqipao.data;

import java.io.Serializable;
import java.util.List;

public class RoomInfoModel implements Serializable {


    /**
     * room_id : 810
     * user_id : 548002
     * room_name : xx
     * password : 0
     * label_id : 22
     * type_id : 4
     * add_time : 2019-12-16 10:48:15
     * update_time : 2019-12-18 12:45:53
     * wheat : 1
     * playing : null
     * greeting :
     * cover_picture :
     * even_wheat : 1
     * voice : 1
     * on_line : 1
     * chatrooms : 101781177696257
     * sys_type_id : 0
     * earnings : 14.40
     * popularity : 67
     * cardiac : 1
     * water_ratio : 0
     * hot_sort : 0
     * seal_time : 0
     * bg_picture : null
     * room_code : 1734069
     * contribution : 120
     * label_name : 开黑
     * type_name : 聊天
     * pit_list : [{"id":"8262","room_id":"810","pit_number":"1","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8263","room_id":"810","pit_number":"2","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8264","room_id":"810","pit_number":"3","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8265","room_id":"810","pit_number":"4","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8266","room_id":"810","pit_number":"5","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8267","room_id":"810","pit_number":"6","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8268","room_id":"810","pit_number":"7","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8269","room_id":"810","pit_number":"8","voice":"0","shutup":"2","state":"2","nickname":null,"head_picture":null,"sex":null,"emchat_username":null,"rank_id":null,"nobility":null,"xin_dong":0},{"id":"8270","room_id":"810","user_id":"548002","pit_number":"9","voice":"1","shutup":"2","state":"2","nickname":"244813","head_picture":"","sex":"1","emchat_username":"17326032805","rank_id":"1","nobility":"0","xin_dong":"120","banned":0}]
     * house_owner : 0
     * user : {"manager":"0","head_picture":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJq9Kq5vXanJDRNTOnMiaAVgXFqgmZ8qhxcLG4rCxdsdbKgl8E0K7JnAL4JGqPOcO2LJcSsyEPQjA/132","nickname":"因为一个人爱上一座城","pit":0,"voice":0,"shutup":2,"favorite":1}
     */

    private String room_id;
    private String user_id;
    private String room_name;
    private int password;
    private String label_id;
    private String type_id;
    private String add_time;
    private String update_time;
    private String wheat;
    private String playing;
    private String greeting;
    private String cover_picture;
    private String even_wheat;
    private String voice;
    private String on_line;
    private String chatrooms;
    private String sys_type_id;
    private String earnings;
    private String popularity;
    private String cardiac;
    private String water_ratio;
    private String hot_sort;
    private String seal_time;
    private Object bg_picture;
    private String room_code;
    private String contribution;
    private String label_name;
    private String type_name;
    private int pit_9;
    private int house_owner;
    private UserBean user;
    private List<PitListBean> pit_list;
    private int banner;

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public int getPit_9() {
        return pit_9;
    }

    public void setPit_9(int pit_9) {
        this.pit_9 = pit_9;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getWheat() {
        return wheat;
    }

    public void setWheat(String wheat) {
        this.wheat = wheat;
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

    public String getCover_picture() {
        return cover_picture;
    }

    public void setCover_picture(String cover_picture) {
        this.cover_picture = cover_picture;
    }

    public String getEven_wheat() {
        return even_wheat;
    }

    public void setEven_wheat(String even_wheat) {
        this.even_wheat = even_wheat;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getOn_line() {
        return on_line;
    }

    public void setOn_line(String on_line) {
        this.on_line = on_line;
    }

    public String getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(String chatrooms) {
        this.chatrooms = chatrooms;
    }

    public String getSys_type_id() {
        return sys_type_id;
    }

    public void setSys_type_id(String sys_type_id) {
        this.sys_type_id = sys_type_id;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getCardiac() {
        return cardiac;
    }

    public void setCardiac(String cardiac) {
        this.cardiac = cardiac;
    }

    public String getWater_ratio() {
        return water_ratio;
    }

    public void setWater_ratio(String water_ratio) {
        this.water_ratio = water_ratio;
    }

    public String getHot_sort() {
        return hot_sort;
    }

    public void setHot_sort(String hot_sort) {
        this.hot_sort = hot_sort;
    }

    public String getSeal_time() {
        return seal_time;
    }

    public void setSeal_time(String seal_time) {
        this.seal_time = seal_time;
    }

    public Object getBg_picture() {
        return bg_picture;
    }

    public void setBg_picture(Object bg_picture) {
        this.bg_picture = bg_picture;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getHouse_owner() {
        return house_owner;
    }

    public void setHouse_owner(int house_owner) {
        this.house_owner = house_owner;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<PitListBean> getPit_list() {
        return pit_list;
    }

    public void setPit_list(List<PitListBean> pit_list) {
        this.pit_list = pit_list;
    }

    public static class UserBean implements Serializable {
        /**
         * manager : 0
         * head_picture : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJq9Kq5vXanJDRNTOnMiaAVgXFqgmZ8qhxcLG4rCxdsdbKgl8E0K7JnAL4JGqPOcO2LJcSsyEPQjA/132
         * nickname : 因为一个人爱上一座城
         * pit : 0
         * voice : 0
         * shutup : 2
         * favorite : 1
         */

        private int manager;
        private String head_picture;
        private String nickname;
        private int pit;
        private int voice;
        private int shutup;
        private String favorite;

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

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }
    }

    public static class PitListBean implements Serializable {
        /**
         * id : 8262
         * room_id : 810
         * pit_number : 1
         * voice : 0
         * shutup : 2
         * state : 2
         * nickname : null
         * head_picture : null
         * sex : null
         * emchat_username : null
         * rank_id : null
         * nobility : null
         * xin_dong : 0
         * user_id : 548002
         * banned : 0
         */

        private String id;
        private String room_id;
        private int pit_number;
        private int voice;
        private int shutup;
        private String state;
        private String nickname;
        private String head_picture;
        private Object sex;
        private Object emchat_username;
        private Object rank_id;
        private Object nobility;
        private String xin_dong;
        private String user_id;
        private int banned;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
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

        public int getShutup() {
            return shutup;
        }

        public void setShutup(int shutup) {
            this.shutup = shutup;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getEmchat_username() {
            return emchat_username;
        }

        public void setEmchat_username(Object emchat_username) {
            this.emchat_username = emchat_username;
        }

        public Object getRank_id() {
            return rank_id;
        }

        public void setRank_id(Object rank_id) {
            this.rank_id = rank_id;
        }

        public Object getNobility() {
            return nobility;
        }

        public void setNobility(Object nobility) {
            this.nobility = nobility;
        }

        public String getXin_dong() {
            return xin_dong;
        }

        public void setXin_dong(String xin_dong) {
            this.xin_dong = xin_dong;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }
    }
}
