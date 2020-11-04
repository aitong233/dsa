package com.qpyy.rtc;

import com.qpyy.libcommon.bean.Config;

public interface Rtc {


    /**
     * 初始化
     *
     * @param rtcType       rtc类型
     * @param scenariosType 应用场景
     */
    void init(int rtcType, int scenariosType, Config config);

    /**
     * 进入房间
     *
     * @param roomId
     * @param userId
     * @param userName
     * @param token
     */
    void loginRoom(String roomId, String userId, String userName, String token);


    /**
     * 离开房间
     */
    void leaveChannel(String roomId);


    /**
     * 设置变声
     *
     * @param type
     */
    void setTone(int type);


    /**
     * 是否开启耳返
     *
     * @param b
     */
    void enableHeadphoneMonitor(boolean b);


    /**
     * 设置耳返音量
     *
     * @param volume
     */
    void setHeadphoneMonitorVolume(int volume);

    /**
     * 设置音乐播放音量
     * @param volume
     */
    void setAudioMixingVolume(int volume);

    /**
     * 播放音乐
     *
     * @param url
     */
    void startAudioMixing(String url);


    /**
     * 停止播放音乐
     */
    void stopAudioMixing();

    /**
     * 暂停音乐播放
     */
    void pauseAudioMixing();

    /**
     * 恢复播放
     */
    void resumeAudioMixing();


    /**
     * 上麦
     *
     * @param streamID
     */
    void applyWheat(String streamID);

    /**
     * 下麦
     */
    void downWheat();


    /**
     * 开麦，闭麦
     *
     * @param b
     */
    void muteLocalAudioStream(boolean b);


    /**
     * 设备静音
     */
    void muteSpeaker(boolean b);


}
