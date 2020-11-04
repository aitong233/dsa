package com.spadea.xqipao.service;

public interface MyEmqttSubscribeListener {

    void onSubscribeSuccess(String topic);

    void onSubscribeFailure();
}
