package com.spadea.xqipao.data.even;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data.even
 * 创建人 王欧
 * 创建时间 2020/4/11 8:32 PM
 * 描述 describe
 */
public class RoomPlayingSetEvent {
    public String roomId;
    public String playing;

    public RoomPlayingSetEvent(String roomId, String playing) {
        this.roomId = roomId;
        this.playing = playing;
    }
}
