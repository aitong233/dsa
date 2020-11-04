package com.spadea.xqipao.data;

public class RoomModel {


    /**
     * room_id : 843
     * room_code : 20777
     * user_id : 548120
     * room_name : 须尽欢女神🌙宝藏女孩零
     * label_id : 25
     * type_id : 0
     * popularity : 1175
     * label_name : 女神
     * owner_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2019-12-23/BDDC0E6A-E90B-4935-8334-A2DDC690030E.png
     * owner_sex : 2
     * holder : 548996
     * holder_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/android_images/548996/20191224190037_1577185235767.jpg
     * holder_sex : 2
     * owner_nickname : 收🎁秒结🌙须尽欢
     * holder_nickname : 兔兔🌙
     * is_owner : 0
     * have_password : 0
     * last_join : {"nickname":"忘川","head_picture":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2019-12-06/3A29FB55-E55B-46EB-B4F0-C65A40C9200B.png"}
     */

    private String room_id;
    private String room_code;
    private String user_id;
    private String room_name;
    private String label_id;
    private String type_id;
    private String popularity;
    private String label_name;
    private String owner_picture;
    private String owner_sex;
    private String holder;
    private String holder_picture;
    private String holder_sex;
    private String owner_nickname;
    private String holder_nickname;
    private int is_owner;
    private int have_password;
    private LastJoinBean last_join;
    private String favorite;
    private int week_star_recommend; //周星推荐  1是0否

    public int getWeek_star_recommend() {
        return week_star_recommend;
    }

    public void setWeek_star_recommend(int week_star_recommend) {
        this.week_star_recommend = week_star_recommend;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getOwner_picture() {
        return owner_picture;
    }

    public void setOwner_picture(String owner_picture) {
        this.owner_picture = owner_picture;
    }

    public String getOwner_sex() {
        return owner_sex;
    }

    public void setOwner_sex(String owner_sex) {
        this.owner_sex = owner_sex;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolder_picture() {
        return holder_picture;
    }

    public void setHolder_picture(String holder_picture) {
        this.holder_picture = holder_picture;
    }

    public String getHolder_sex() {
        return holder_sex;
    }

    public void setHolder_sex(String holder_sex) {
        this.holder_sex = holder_sex;
    }

    public String getOwner_nickname() {
        return owner_nickname;
    }

    public void setOwner_nickname(String owner_nickname) {
        this.owner_nickname = owner_nickname;
    }

    public String getHolder_nickname() {
        return holder_nickname;
    }

    public void setHolder_nickname(String holder_nickname) {
        this.holder_nickname = holder_nickname;
    }

    public int getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(int is_owner) {
        this.is_owner = is_owner;
    }

    public int getHave_password() {
        return have_password;
    }

    public void setHave_password(int have_password) {
        this.have_password = have_password;
    }

    public LastJoinBean getLast_join() {
        return last_join;
    }

    public void setLast_join(LastJoinBean last_join) {
        this.last_join = last_join;
    }

    public static class LastJoinBean {
        /**
         * nickname : 忘川
         * head_picture : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/ios_images/2019-12-06/3A29FB55-E55B-46EB-B4F0-C65A40C9200B.png
         */

        private String nickname;
        private String head_picture;

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
}
