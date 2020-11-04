package com.qpyy.module.me.bean;

import java.util.List;

import top.defaults.view.PickerView;

public class RegionListResp implements PickerView.PickerItem {

    private String id;
    private String region_name;

    private List<CityResp> children;


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

    public List<CityResp> getChildren() {
        return children;
    }

    public void setChildren(List<CityResp> children) {
        this.children = children;
    }

    @Override
    public String getText() {
        return region_name;
    }
}
