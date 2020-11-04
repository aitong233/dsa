package com.qpyy.module.me.bean;

import top.defaults.view.PickerView;

public class CityResp implements PickerView.PickerItem  {

    private String id;
    private String region_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    @Override
    public String getText() {
        return region_name;
    }
}
