package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomBanWheatModel;
import com.qpyy.libcommon.event.RoomBanWheatEvent;
import com.qpyy.libcommon.event.RoomUserBanWheatEvent;
import com.qpyy.libcommon.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

public class RoomBanWheatRunnable implements Runnable {

    private String data;

    public RoomBanWheatRunnable(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        RoomBanWheatModel roomBanWheatModel = JSON.parseObject(data, RoomBanWheatModel.class);
        EventBus.getDefault().post(new RoomBanWheatEvent(roomBanWheatModel.getRoom_id(), roomBanWheatModel.getPit_number(), "1".equals(roomBanWheatModel.getAction())));
        String userId = SpUtils.getUserId();
        if (userId.equals(roomBanWheatModel.getUser_id())) {
            EventBus.getDefault().post(new RoomUserBanWheatEvent(roomBanWheatModel.getRoom_id(), roomBanWheatModel.getUser_id(), "1".equals(roomBanWheatModel.getAction())));
        }
    }
}
