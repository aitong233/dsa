package com.spadea.xqipao.data;

public class MyProductsModel {


    /**
     * id : 36
     * price : 80
     * day : 15
     * state : 0
     * expired_time : 0
     * add_time : 2020-01-02
     * title : 气饱1
     * catetory_title : 黑桃A
     * picture : http://yutangyuyin1.oss-cn-hangzhou.aliyuncs.com/admin_images/5e0d54da1574f.jpg
     * category_id : 9
     */

    private String id;
    private String price;
    private String day;
    private String state;
    private String expired_time;
    private String add_time;
    private String title;
    private String catetory_title;
    private String picture;
    private String category_id;
    private String left_time;

    public String getLeft_time() {
        return left_time;
    }

    public void setLeft_time(String left_time) {
        this.left_time = left_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(String expired_time) {
        this.expired_time = expired_time;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatetory_title() {
        return catetory_title;
    }

    public void setCatetory_title(String catetory_title) {
        this.catetory_title = catetory_title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
