package com.qpyy.room.event;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.event
 * 创建人 王欧
 * 创建时间 2020/8/17 9:14 AM
 * 描述 describe
 */
public class SoundLevelEvent {
    public int volume;
    public String userId;

    public SoundLevelEvent(String userId, int volume) {
        this.userId = userId;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "SoundLevelEvent{" +
                "volume=" + volume +
                ", userId='" + userId + '\'' +
                '}';
    }
}
