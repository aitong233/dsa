package com.qpyy.libcommon.bean;

import java.util.List;

public class UserBean {
    public static final String FEMALE="2";
    public static final String MALE="1";
    private String user_id;
    private String mobile;
    private String user_no;
    private String parent_id;
    private String rank_id;
    private String emchat_username;
    private String emchat_password;
    private int is_password;
    private int sex;
    private String head_picture;
    private int photo_count;
    private String signature;
    private String birthday;
    private String constellation;
    private String profession;
    private String province_id;
    private String city_id;
    private String county_id;
    private String province;
    private String city;
    private String county;
    private String real_name;
    private String identity_number;
    private String token;
    private int broadcast;
    private int fans;
    private int news_voice;
    private int news_vibrate;
    private int only_friend;
    private int visit;
    private int online;
    private int user_is_new;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public int getNews_voice() {
        return news_voice;
    }

    public void setNews_voice(int news_voice) {
        this.news_voice = news_voice;
    }

    public int getNews_vibrate() {
        return news_vibrate;
    }

    public void setNews_vibrate(int news_vibrate) {
        this.news_vibrate = news_vibrate;
    }

    private int chat;
    private String friend;
    private String guanzhu;
    private String fensi;
    private String come_count;
    private List<String> user_photo;
    private String nickname;
    private String money;
    private String nobility_name;
    private String expiration_time;
    private String gold;
    private String next_experience;
    private int role;
    private int age;
    private String earnings;
    private String user_code;
    private String withdraw_earnings;
    private int second_password; //1已设置0未设置
    private int label_count;

    private String login_type;

    private RankInfo rank_info;

    public RankInfo getRank_info() {
        return rank_info;
    }

    public void setRank_info(RankInfo rank_info) {
        this.rank_info = rank_info;
    }

    public int getLabel_count() {
        return label_count;
    }

    public void setLabel_count(int label_count) {
        this.label_count = label_count;
    }

    public int getSecond_password() {
        return second_password;
    }

    public void setSecond_password(int second_password) {
        this.second_password = second_password;
    }

    public String getWithdraw_earnings() {
        return withdraw_earnings;
    }

    public void setWithdraw_earnings(String withdraw_earnings) {
        this.withdraw_earnings = withdraw_earnings;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNobility_name() {
        return nobility_name;
    }

    public void setNobility_name(String nobility_name) {
        this.nobility_name = nobility_name;
    }

    public String getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(String expiration_time) {
        this.expiration_time = expiration_time;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getNext_experience() {
        return next_experience;
    }

    public void setNext_experience(String next_experience) {
        this.next_experience = next_experience;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
    }

    public String getEmchat_password() {
        return emchat_password;
    }

    public void setEmchat_password(String emchat_password) {
        this.emchat_password = emchat_password;
    }

    public int getIs_password() {
        return is_password;
    }

    public void setIs_password(int is_password) {
        this.is_password = is_password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public int getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(int photo_count) {
        this.photo_count = photo_count;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(int broadcast) {
        this.broadcast = broadcast;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getGuanzhu() {
        return guanzhu;
    }

    public void setGuanzhu(String guanzhu) {
        this.guanzhu = guanzhu;
    }

    public String getFensi() {
        return fensi;
    }

    public void setFensi(String fensi) {
        this.fensi = fensi;
    }

    public String getCome_count() {
        return come_count;
    }

    public void setCome_count(String come_count) {
        this.come_count = come_count;
    }

    public List<String> getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(List<String> user_photo) {
        this.user_photo = user_photo;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }
}
