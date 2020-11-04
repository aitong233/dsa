package com.yutang.game.fudai.bean;

public class EggGiftModel {

    /**
     * gift_id : 13
     * prize_title : 女神面罩
     * picture : http://fishpond.pro1.lnkj1.com/Uploads/Picture/UID/20191009_082231_15705805516364_8488.jpg
     * number : 1
     */

    private int gift_id;
    private String prize_title;
    private String picture;
    private int number;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public String getPrize_title() {
        return prize_title;
    }

    public void setPrize_title(String prize_title) {
        this.prize_title = prize_title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
