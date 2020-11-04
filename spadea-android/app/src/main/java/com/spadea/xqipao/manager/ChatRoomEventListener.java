package com.spadea.xqipao.manager;

public interface ChatRoomEventListener {

    void onAudioMixingStateChanged(int state);

    void onAudioVolumeIndication(String userId, int volume);
}
