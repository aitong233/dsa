package com.spadea.xqipao.data;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/13 6:20 PM
 * 描述 describe
 */
public class RoomBgBean {

    /**
     * id : 1
     * picture : http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/SNBcGNH72e.jpg
     */

    private String id;
    private String picture;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
