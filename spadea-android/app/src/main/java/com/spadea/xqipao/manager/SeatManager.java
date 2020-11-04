package com.spadea.xqipao.manager;

import io.agora.rtc.Constants;

public abstract class SeatManager {


    abstract RtcManager getRtcManager();

    public abstract void muteMic(boolean muted);

    public abstract void setClientRole(int role);

    public abstract void joinChannel(String channelId);

    public abstract void setLocalVoiceReverbPreset(int audioReverbOff);

    public abstract void setLocalVoiceChanger(int voiceChangerOff);

    public abstract void setClodeLocalVoice();

    public abstract void leaveChannel();

    public abstract void enableInEarMonitoring(boolean b);

    public void setLocalVoiceReverbPreset() {
        getRtcManager().setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_OFF);
    }

    public void setLocalVoiceChanger() {
        getRtcManager().setLocalVoiceChanger(Constants.VOICE_CHANGER_OFF);
    }


}
