package com.qpyy.libcommon.service;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomClearCardiacAllModel;
import com.qpyy.libcommon.bean.RoomClearCardiacModel;

import org.greenrobot.eventbus.EventBus;

public class RoomClearCardiacRunnable implements Runnable {

    private String data;

    public RoomClearCardiacRunnable(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        RoomClearCardiacModel roomClearCardiacModel = JSON.parseObject(data, RoomClearCardiacModel.class);
        if (TextUtils.isEmpty(roomClearCardiacModel.getPit_number())) {
            EventBus.getDefault().post(new RoomClearCardiacAllModel(roomClearCardiacModel.getRoom_id()));
        } else {
            EventBus.getDefault().post(roomClearCardiacModel);
        }
    }
}
