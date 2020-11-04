package com.qpyy.module.me.bean;

import top.defaults.view.PickerView;

public class ProfessionBean implements PickerView.PickerItem {

    private String text;

    public ProfessionBean(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
