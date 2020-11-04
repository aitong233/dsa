package com.spadea.xqipao.data;


public class GameLog {

    /**
     * gift_id : 249
     * count : 1
     * created_at : 2020
     * name : 666
     * picture : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182edc801f1.png
     * special : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f182ee00576f.png
     */

    private int gift_id;
    private int count;
    private String created_at;
    private String name;
    private String picture;
    private String special;

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
