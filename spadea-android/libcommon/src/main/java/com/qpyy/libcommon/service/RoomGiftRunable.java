package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomGiftModel;
import com.qpyy.libcommon.event.RoomGiftEvent;

import org.greenrobot.eventbus.EventBus;

public class RoomGiftRunable implements Runnable {

    private String data;

    public RoomGiftRunable(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        RoomGiftModel roomGiftModel = JSON.parseObject(data, RoomGiftModel.class);
        for (RoomGiftModel.ListBean item : roomGiftModel.getList()) {
            EventBus.getDefault().post(new RoomGiftEvent(roomGiftModel.getRoom_id(), item));
        }
    }
}
