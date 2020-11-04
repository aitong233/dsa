package com.qpyy.rtc;

import im.zego.zegoexpress.entity.ZegoBarrageMessageInfo;
import im.zego.zegoexpress.entity.ZegoBroadcastMessageInfo;
import im.zego.zegoexpress.entity.ZegoPlayStreamQuality;
import im.zego.zegoexpress.entity.ZegoPublishStreamQuality;
import im.zego.zegoexpress.entity.ZegoStream;
import im.zego.zegoexpress.entity.ZegoUser;

public interface RtcEventListener {


    /**
     * 断开连接
     */
    void onRoomDisConnect();

    /**
     * 已连接
     */
    void onRoomConnected();

    /**
     * 连接中
     */
    void onRoomconnecting();


    /**
     * 远端拉流音频声浪回调
     */
    void onRemoteSoundLevelUpdate(String streamID, int soundLevel);

    /**
     * 不在播放中
     */
    void noPlay();

    /**
     * 暂停播放
     */
    void pausIng();

    /**
     * 播放中
     */
    void playIng();

    /**
     * 播放结束
     */
    void playEnded();

    void destroy();

}
