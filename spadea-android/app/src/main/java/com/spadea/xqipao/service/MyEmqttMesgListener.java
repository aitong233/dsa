package com.spadea.xqipao.service;

public interface MyEmqttMesgListener {

    void messageArrived(String topic, String mesg);

}
