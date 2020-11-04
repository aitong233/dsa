package com.spadea.xqipao.data;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class JueInfo extends SimpleBannerInfo {


    public JueInfo(int res, int type) {
        this.res = res;
        this.type = type;
    }

    private int res;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
