package com.spadea.xqipao.data;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class XBannerImg extends SimpleBannerInfo {



    private String imgUrl;

    public XBannerImg(  String imgUrl) {

        this.imgUrl = imgUrl;
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
