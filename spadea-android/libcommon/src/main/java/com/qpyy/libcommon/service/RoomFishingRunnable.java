package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomFishingModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RoomFishingRunnable implements Runnable {
    private String data;
    private int type;

    public RoomFishingRunnable(String data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public void run() {
        if(type == 5021) {
            List<RoomFishingModel> roomFishingModels = JSON.parseArray(data, RoomFishingModel.class);
            for (RoomFishingModel item : roomFishingModels) {
                EventBus.getDefault().post(item);
            }
        }
        if(type == 5049 || type == 5151){
            RoomFishingModel roomFishingModel = JSON.parseObject(data, RoomFishingModel.class);
            EventBus.getDefault().post(roomFishingModel);
        }
    }
}
