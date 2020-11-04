package com.qpyy.rtc.zego;

import com.qpyy.rtc.RtcEventListener;

import im.zego.zegoexpress.ZegoMediaPlayer;
import im.zego.zegoexpress.callback.IZegoMediaPlayerEventHandler;
import im.zego.zegoexpress.constants.ZegoMediaPlayerState;

public class MyIZegoMediaPlayerEventHandler extends IZegoMediaPlayerEventHandler {
    private RtcEventListener mRtcEventListener;

    public MyIZegoMediaPlayerEventHandler(RtcEventListener rtcEventListener) {
        this.mRtcEventListener = rtcEventListener;
    }

    @Override
    public void onMediaPlayerStateUpdate(ZegoMediaPlayer mediaPlayer, ZegoMediaPlayerState state, int errorCode) {
        if (mRtcEventListener != null) {
            switch (state) {
                case NO_PLAY:
                    mRtcEventListener.noPlay();
                    break;
                case PAUSING:
                    mRtcEventListener.pausIng();
                    break;
                case PLAYING:
                    mRtcEventListener.playIng();
                    break;
                case PLAY_ENDED:
                    mRtcEventListener.playEnded();
                    break;
            }
        }
    }

}
