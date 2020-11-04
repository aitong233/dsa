package com.qpyy.libcommon.event;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.event
 * 创建人 王欧
 * 创建时间 2020/7/9 5:36 PM
 * 描述 describe
 */
public class ConversationDelEvent {
    public int position;
    public boolean delete;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ConversationDelEvent(int position) {
        this.position = position;
        this.delete=true;
    }

    public ConversationDelEvent(int position, boolean delete) {
        this.position = position;
        this.delete = delete;
    }
}
