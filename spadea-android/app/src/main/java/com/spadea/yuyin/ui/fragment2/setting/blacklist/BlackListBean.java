package com.spadea.yuyin.ui.fragment2.setting.blacklist;

public class BlackListBean {

    /**
     * id : 2
     * black_id : 546003
     * nickname : mixed
     * head_picture : mixed
     * sex : mixed
     * age : mixed
     * signature : mixed
     */

    private String id;
    private String black_id;
    private String nickname;
    private String head_picture;
    private int sex;
    private int age;
    private String signature;
    private String emchat_username;

    public BlackListBean() {
    }

    public BlackListBean(String nickname) {
        this.nickname = nickname;
    }

    public String getEmchat_username() {
        return emchat_username;
    }

    public void setEmchat_username(String emchat_username) {
        this.emchat_username = emchat_username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlack_id() {
        return black_id;
    }

    public void setBlack_id(String black_id) {
        this.black_id = black_id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
