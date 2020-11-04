package com.qpyy.module.me.bean;

import top.defaults.view.PickerView;

public class ConstellationBean implements PickerView.PickerItem{

    private String text;


    public ConstellationBean(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
