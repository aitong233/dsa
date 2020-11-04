package com.spadea.xqipao.utils.view.room.animation;

public class ItemRoomGiftBean {

    public ItemRoomGiftBean() {

    }

    public ItemRoomGiftBean(String formUser, String toUser, String num, String giftImgUrl) {
        this.formUser = formUser;
        this.toUser = toUser;
        this.num = num;
        this.giftImgUrl = giftImgUrl;
    }

    private String formUser;
    private String toUser;
    private String num;
    private String giftImgUrl;

    public String getFormUser() {
        return formUser;
    }

    public void setFormUser(String formUser) {
        this.formUser = formUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGiftImgUrl() {
        return giftImgUrl;
    }

    public void setGiftImgUrl(String giftImgUrl) {
        this.giftImgUrl = giftImgUrl;
    }

    @Override
    public String toString() {
        return "ItemRoomGiftBean{" +
                "formUser='" + formUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", num='" + num + '\'' +
                ", giftImgUrl='" + giftImgUrl + '\'' +
                '}';
    }
}
