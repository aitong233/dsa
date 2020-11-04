package com.qpyy.module_news.event;

import com.qpyy.libcommon.bean.GiftModel;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module_news.event
 * 创建人 王欧
 * 创建时间 2020/7/15 2:25 PM
 * 描述 describe
 */
public class GiftMsgEvent {
    public GiftModel giftModel;
    public String num;

    public GiftMsgEvent(GiftModel giftModel, String num) {
        this.giftModel = giftModel;
        this.num = num;
    }
}
