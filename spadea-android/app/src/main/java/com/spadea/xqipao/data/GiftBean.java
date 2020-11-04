package com.spadea.xqipao.data;

public class GiftBean {

    /**
     * id : 5
     * name : 小贝壳
     * picture : http://fishpond.pro1.lnkj1.com/Uploads/Picture/UID/20190909_151513_15680133130553_3032.jpg
     * price : 710
     */

    private String id;
    private String gift_id;
    private String name;
    private String picture;
    private String price;
    private boolean isChecked;
    private String special;
    private int number;
    private String pits;
    private String room_id;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getPits() {
        return pits;
    }

    public void setPits(String pits) {
        this.pits = pits;
    }

    public String getGift_id() {
        return gift_id;
    }

    public void setGift_id(String gift_id) {
        this.gift_id = gift_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
