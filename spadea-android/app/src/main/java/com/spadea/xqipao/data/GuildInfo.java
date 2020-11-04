package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/5/8 4:45 PM
 * 描述 describe
 */
public class GuildInfo {


    /**
     * id : 1
     * unionNum : 547900
     * userId : 547900
     * type : 1
     * password : 295EFD49A617451594D2581224B808F5719DED7EAE55FF172A919F39
     * unionName : test1
     * deleted : 0
     * state : 1
     * token : null
     * createdTime : 2020-05-20T15:06:53
     * updatedTime : null
     */

    private int id;
    private int unionNum;
    private int userId;
    private int type;
    private String password;
    private String unionName;
    private int deleted;
    private int state;
    private String createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnionNum() {
        return unionNum;
    }

    public void setUnionNum(int unionNum) {
        this.unionNum = unionNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
