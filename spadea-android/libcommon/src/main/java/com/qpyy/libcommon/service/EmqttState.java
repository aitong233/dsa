package com.qpyy.libcommon.service;

public enum EmqttState {

    DISCONNECT(0), //断开连接
    CONNECTED(1),//已连接
    TOPICS_SUCCESS(2),//订阅成功
    TOPICS_FAIL(3),//订阅失败
    CANCEL_TOPICS_SUCCESS(4),//取消订阅成功
    CANCEL_TOPICS_FAIL(5),//取消订阅失败
    CLOSE_SUCCESS(6),//关闭成功
    CLOSE_FAIL(7),
    ;//关闭失败

    private int value;

    EmqttState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
