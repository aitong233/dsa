package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.libcommon.event.RoomContributionEvent;

import org.greenrobot.eventbus.EventBus;

public class RoomGiveGiftRunnable implements Runnable {

    private String data;

    public RoomGiveGiftRunnable(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        RoomGiveGiftModel roomGiveGiftModel = JSON.parseObject(data, RoomGiveGiftModel.class);
        EventBus.getDefault().post(new RoomContributionEvent(roomGiveGiftModel.getRoom_id(), roomGiveGiftModel.getContribution()));
        for (RoomGiveGiftModel.GiftListBean item : roomGiveGiftModel.getGift_list()) {
            EventBus.getDefault().post(item);
        }
        for (RoomGiveGiftModel.CardiacListBean item : roomGiveGiftModel.getCardiac_list()) {
            EventBus.getDefault().post(item);
        }
    }
}
