package com.qpyy.module.me.bean;

import top.defaults.view.PickerView;

public class DateBean implements PickerView.PickerItem {

    private String text;
    private int date;

    public DateBean(String text, int date) {
        this.text = text;
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return "DateBean{" +
                "text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
