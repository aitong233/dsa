package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/6 13:36
 * 描述 describe
 */
public class GiftNumBean {
    private String number;
    private String text;

    public GiftNumBean() {
    }

    public GiftNumBean(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
