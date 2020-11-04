package com.spadea.xqipao.data;

import com.qpyy.libcommon.bean.RankInfo;

import java.util.List;

public class RoomExtraModel {


    /**
     * manager : 0
     * favorite : 1
     * chatrooms : 100723521028097
     * manager_list : [{"user_id":"547985","user_code":"173874","sex":"1","nickname":"啊啊啊啊啊啊啊啊","head_picture":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqO9bwI75jV3aC1HlLCkkf56lunkOST4onjjC92BE5E4hn3q8hB3UFMXzD2chnVAqE1Vx31rIjcFA/132"}]
     * black_list : []
     * room_id : 795
     * room_code : 8339126
     * popularity : 30
     * room_name : xx
     * cover_picture :
     * bg_picture : null
     * is_password : 0
     * playing : null
     * label_id : 22
     * label_name : 开黑
     * label_list : [{"id":"24","label_name":"男神"},{"id":"25","label_name":"女神"},{"id":"26","label_name":"相亲"},{"id":"27","label_name":"电台"},{"id":"28","label_name":"娱乐"},{"id":"29","label_name":"交友"},{"id":"30","label_name":"点唱"},{"id":"31","label_name":"派单"},{"id":"32","label_name":"官方"}]
     */

    private int manager;
    private int favorite;
    private String chatrooms;
    private String room_id;
    private String room_code;
    private String popularity;
    private String room_name;
    private String cover_picture;
    private String bg_picture;
    private int is_password;
    private String playing;
    private String label_id;
    private String label_name;
    private List<ManagerListBean> manager_list;
    private List<ManagerListBean> black_list;
    private List<LabelListBean> label_list;
    private String head_picture;
    private String user_id;
    private String user_code;
    private List<BgBean> bg_list;
    private String wheat;
    private String official_notice;
    private String greeting;
    private String type_id;
    private String type_name;
    private List<TypeBean> type_list;
    private RankInfo rank_info;
    private int show_ball_game;

    public int getShow_ball_game() {
        return show_ball_game;
    }

    public void setShow_ball_game(int show_ball_game) {
        this.show_ball_game = show_ball_game;
    }

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<TypeBean> getType_list() {
        return type_list;
    }

    public void setType_list(List<TypeBean> type_list) {
        this.type_list = type_list;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getOfficial_notice() {
        return official_notice;
    }

    public void setOfficial_notice(String official_notice) {
        this.official_notice = official_notice;
    }

    public String getWheat() {
        return wheat;
    }

    public void setWheat(String wheat) {
        this.wheat = wheat;
    }

    public List<BgBean> getBg_list() {
        return bg_list;
    }

    public void setBg_list(List<BgBean> bg_list) {
        this.bg_list = bg_list;
    }

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

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(String chatrooms) {
        this.chatrooms = chatrooms;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getCover_picture() {
        return cover_picture;
    }

    public void setCover_picture(String cover_picture) {
        this.cover_picture = cover_picture;
    }

    public String getBg_picture() {
        return bg_picture;
    }

    public void setBg_picture(String bg_picture) {
        this.bg_picture = bg_picture;
    }

    public int getIs_password() {
        return is_password;
    }

    public void setIs_password(int is_password) {
        this.is_password = is_password;
    }

    public String getPlaying() {
        return playing;
    }

    public void setPlaying(String playing) {
        this.playing = playing;
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

    public List<ManagerListBean> getManager_list() {
        return manager_list;
    }

    public void setManager_list(List<ManagerListBean> manager_list) {
        this.manager_list = manager_list;
    }

    public List<ManagerListBean> getBlack_list() {
        return black_list;
    }

    public void setBlack_list(List<ManagerListBean> black_list) {
        this.black_list = black_list;
    }

    public List<LabelListBean> getLabel_list() {
        return label_list;
    }

    public void setLabel_list(List<LabelListBean> label_list) {
        this.label_list = label_list;
    }

    public static class TypeBean {
        private String id;
        private String type_name;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class BgBean {
        private String id;
        private String picture;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }


    public static class ManagerListBean {
        /**
         * user_id : 547985
         * user_code : 173874
         * sex : 1
         * nickname : 啊啊啊啊啊啊啊啊
         * head_picture : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqO9bwI75jV3aC1HlLCkkf56lunkOST4onjjC92BE5E4hn3q8hB3UFMXzD2chnVAqE1Vx31rIjcFA/132
         */

        private String user_id;
        private String user_code;
        private String sex;
        private String nickname;
        private String head_picture;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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
    }

    public static class LabelListBean {
        /**
         * id : 24
         * label_name : 男神
         */

        private String id;
        private String label_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel_name() {
            return label_name;
        }

        public void setLabel_name(String label_name) {
            this.label_name = label_name;
        }
    }
}
