package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/8 3:42 PM
 * 描述 describe
 */
public class MyGuildInfo {

    /**
     * id : 537
     * unionName : null
     * headPicture : null
     * userId : 579046
     * userName : null
     * createdTime : 2020-05-21T00:29:52
     * labelId : null
     * labelName : null
     * unionNum : 661214
     * inUnionDate : 0
     */

    private int id;
    private String unionName;
    private String headPicture;
    private int userId;
    private String userName;
    private String createdTime;
    private String labelId;
    private String labelName;
    private int unionNum;
    private int inUnionDate;
    private int unionId;

    public int getUnionId() {
        return unionId;
    }

    public void setUnionId(int unionId) {
        this.unionId = unionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getUnionNum() {
        return unionNum;
    }

    public void setUnionNum(int unionNum) {
        this.unionNum = unionNum;
    }

    public int getInUnionDate() {
        return inUnionDate;
    }

    public void setInUnionDate(int inUnionDate) {
        this.inUnionDate = inUnionDate;
    }
}
