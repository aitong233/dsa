package com.qpyy.rtc.agora;

import android.os.Handler;
import android.os.Looper;

import com.qpyy.rtc.RtcEventListener;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;

public class MyIRtcEngineEventHandler extends IRtcEngineEventHandler {

    private RtcEventListener mRtcEventListener;

    private Handler handler = new Handler(Looper.getMainLooper());

    public MyIRtcEngineEventHandler(RtcEventListener mRtcEventListener) {
        this.mRtcEventListener = mRtcEventListener;
    }

    /**
     * 连接状态
     *
     * @param state
     * @param reason
     */
    @Override
    public void onConnectionStateChanged(final int state, int reason) {
        try {
            if (mRtcEventListener != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (state) {
                            case Constants.CONNECTION_STATE_CONNECTED:
                                mRtcEventListener.onRoomConnected();
                                break;
                            case Constants.CONNECTION_STATE_DISCONNECTED:
                                mRtcEventListener.onRoomDisConnect();
                                break;
                            case Constants.CONNECTION_STATE_CONNECTING:
                                mRtcEventListener.onRoomconnecting();
                                break;
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 声浪
     *
     * @param speakers
     * @param totalVolume
     */
    @Override
    public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
        for (final AudioVolumeInfo info : speakers) {
            if (mRtcEventListener != null)
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mRtcEventListener.onRemoteSoundLevelUpdate(String.valueOf(info.uid), info.volume);
                    }
                });

        }
    }

    @Override
    public void onAudioMixingStateChanged(final int state, int errorCode) {
        if (mRtcEventListener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    switch (state) {
                        case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_PLAY:
                            mRtcEventListener.playIng();
                            break;
                        case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_PAUSED:
                            mRtcEventListener.pausIng();
                            break;
                        case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_STOPPED:
                            mRtcEventListener.playEnded();
                            break;
                    }
                }
            });
        }
    }
}
