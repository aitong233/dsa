package com.spadea.xqipao.data;

import android.support.annotation.DrawableRes;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data
 * 创建人 王欧
 * 创建时间 2020/4/27 7:39 PM
 * 描述 describe
 */
public class RoomBannerModel extends SimpleBannerInfo {
    public @DrawableRes
    int res;

    public RoomBannerModel(int res) {
        this.res = res;
    }

    @Override
    public Object getXBannerUrl() {
        return res;
    }
}
