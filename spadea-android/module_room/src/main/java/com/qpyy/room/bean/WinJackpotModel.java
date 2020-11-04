package com.qpyy.room.bean;

public class WinJackpotModel {


    /**
     * gift_id : 300
     * name : 小黄鸭
     * price : 10
     * picture : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f1835d82906f.png
     * special : http://menglongyytest.oss-cn-hangzhou.aliyuncs.com/admin_images/5f1835f5c4ebc.png
     * gift_quality : 白色
     */

    private String gift_id;
    private String name;
    private String price;
    private String picture;
    private String special;
    private String gift_quality;

    public String getGift_id() {
        return gift_id;
    }

    public void setGift_id(String gift_id) {
        this.gift_id = gift_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getGift_quality() {
        return gift_quality;
    }

    public void setGift_quality(String gift_quality) {
        this.gift_quality = gift_quality;
    }
}
