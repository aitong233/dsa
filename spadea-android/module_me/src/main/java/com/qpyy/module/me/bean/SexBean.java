package com.qpyy.module.me.bean;

import top.defaults.view.PickerView;

public class SexBean implements PickerView.PickerItem {


    private String sex;
    private int type;


    public SexBean(String sex, int type) {
        this.sex = sex;
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getText() {
        return sex;
    }
}
