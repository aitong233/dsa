package com.qpyy.module.me.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;

public class XBannerData extends SimpleBannerInfo implements Serializable {


    private int type;
    private String url;
    private String vedioCover;

    public XBannerData(int type, String url, String vedioCover) {
        this.type = type;
        this.url = url;
        this.vedioCover = vedioCover;
    }

    public String getVedioCover() {
        return vedioCover;
    }

    public void setVedioCover(String vedioCover) {
        this.vedioCover = vedioCover;
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
        return url;
    }
}
