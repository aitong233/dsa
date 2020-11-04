package com.spadea.xqipao.manager;

import android.os.Handler;
import android.os.Looper;

import com.spadea.yuyin.MyApplication;

import io.agora.rtc.Constants;

public class ChatRoomManager extends SeatManager {

    private final String TAG = ChatRoomManager.class.getSimpleName();
    private static ChatRoomManager instance;
    private RtcManager mRtcManager;
    private ChatRoomEventListener mListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public RtcManager getRtcManager() {
        return mRtcManager;
    }

    @Override
    public void muteMic(boolean muted) {
        mRtcManager.muteLocalAudioStream(muted);
    }

    @Override
    public void setClientRole(int role) {
        mRtcManager.setClientRole(role);
    }

    @Override
    public void setLocalVoiceReverbPreset() {
        mRtcManager.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_OFF);
    }

    private ChatRoomManager() {
        mRtcManager = RtcManager.instance(MyApplication.getInstance());
        mRtcManager.setListener(mRtcListener);
    }


    public static ChatRoomManager instance() {
        if (instance == null) {
            synchronized (ChatRoomManager.class) {
                if (instance == null)
                    instance = new ChatRoomManager();
            }
        }
        return instance;
    }

    public void setListener(ChatRoomEventListener listener) {
        mListener = listener;
    }

    @Override
    public void joinChannel(String channelId) {
        mRtcManager.joinChannel(channelId, Integer.parseInt(MyApplication.getInstance().getUser().getUser_id()));
    }


    private RtcManager.RtcEventListener mRtcListener = new RtcManager.RtcEventListener() {
        @Override
        public void onJoinChannelSuccess(String channelId) {

        }

        @Override
        public void onUserOnlineStateChanged(int uid, boolean isOnline) {

        }

        @Override
        public void onUserMuteAudio(int uid, boolean muted) {

        }

        @Override
        public void onAudioMixingStateChanged(int state) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mListener != null) {
                        mListener.onAudioMixingStateChanged(state);
                    }
                }
            });
        }

        @Override
        public void onAudioVolumeIndication(int uid, int volume) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    if (mListener != null) {
                        mListener.onAudioVolumeIndication(String.valueOf(uid), volume);
                    }
                }
            });
        }
    };

    @Override
    public void setLocalVoiceReverbPreset(int audioReverbOff) {
        setLocalVoiceReverbPreset();
        setLocalVoiceChanger();
        mRtcManager.setLocalVoiceChanger(audioReverbOff);
    }

    @Override
    public void setLocalVoiceChanger(int voiceChangerOff) {
        setLocalVoiceReverbPreset();
        setLocalVoiceChanger();
        mRtcManager.setLocalVoiceChanger(voiceChangerOff);
    }

    @Override
    public void setClodeLocalVoice() {
        setLocalVoiceReverbPreset();
        setLocalVoiceChanger();
    }

    @Override
    public void leaveChannel() {
        mRtcManager.leaveChannel();
        mListener = null;
    }

    @Override
    public void enableInEarMonitoring(boolean b) {
        mRtcManager.enableInEarMonitoring(b);
    }
}
