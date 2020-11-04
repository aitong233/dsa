package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.bean.RoomManagerModel;

import org.greenrobot.eventbus.EventBus;

public class RoomManagerRunnable implements Runnable {

    private String data;
    //0 添加  1删除
    private int type;

    public RoomManagerRunnable(String data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public void run() {
        RoomManagerModel roomManagerModel = JSON.parseObject(data, RoomManagerModel.class);
        if (BaseApplication.getIns().getUser().getUser_id().equals(roomManagerModel.getUser_id())) {
            roomManagerModel.setType(type);
            EventBus.getDefault().post(roomManagerModel);
        }
    }
}
