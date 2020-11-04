package com.qpyy.room.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;

public class BannerItem extends SimpleBannerInfo implements Serializable {
    private String picture;
    private int type;
    private String url;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return picture;
    }
}
