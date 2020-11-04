package com.qpyy.libcommon.service;

import com.alibaba.fastjson.JSON;
import com.qpyy.libcommon.bean.RoomPopularityModel;

import org.greenrobot.eventbus.EventBus;

/**
 * 普通解析通知
 *
 * @author xf
 */
public class OrdinaryNoticeRunable<T> implements Runnable {

    private String data;
    private Class<T> clazz;

    public OrdinaryNoticeRunable(String data, Class<T> clazz) {
        this.data = data;
        this.clazz = clazz;
    }

    @Override
    public void run() {
        EventBus.getDefault().post(JSON.parseObject(data, clazz));
    }
}
