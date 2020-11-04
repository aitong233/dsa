package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/6/5 10:44 AM
 * 描述 describe
 */
public class UserSkillInfo {

    /**
     * id : 47
     * groupId : 2
     * skillId : 53
     * price : 3.00
     * number : 1
     * company : 局
     * skillName : 123
     * userId : 642647
     * emchatUsername : null
     * sex : null
     * headPicture : null
     * nickname : null
     */

    private int id;
    private int groupId;
    private int skillId;
    private int price;
    private int number;
    private String company;
    private String skillName;
    private int userId;
    private String emchatUsername;
    private int sex;
    private String headPicture;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmchatUsername() {
        return emchatUsername;
    }

    public void setEmchatUsername(String emchatUsername) {
        this.emchatUsername = emchatUsername;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
