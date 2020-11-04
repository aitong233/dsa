package com.spadea.xqipao.manager;

import android.content.Context;
import android.util.Log;

import com.spadea.yuyin.BuildConfig;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.utils.SPUtil;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

public final class RtcManager {


    public interface RtcEventListener {
        void onJoinChannelSuccess(String channelId);

        void onUserOnlineStateChanged(int uid, boolean isOnline);

        void onUserMuteAudio(int uid, boolean muted);

        void onAudioMixingStateChanged(int state);

        void onAudioVolumeIndication(int uid, int volume);
    }

    private final String TAG = RtcManager.class.getSimpleName();
    private static RtcManager instance;
    private Context mContext;
    private RtcEventListener mListener;
    private RtcEngine mRtcEngine;
    private int mUserId;

    private RtcManager(Context context) {
        mContext = context.getApplicationContext();
    }

    public static RtcManager instance(Context context) {
        if (instance == null) {
            synchronized (RtcManager.class) {
                if (instance == null)
                    instance = new RtcManager(context);
            }
        }
        return instance;
    }

    public void setListener(RtcEventListener listener) {
        mListener = listener;
    }

    public void init() {
        if (mRtcEngine == null) {
            try {
                mRtcEngine = RtcEngine.create(mContext, BuildConfig.AGORA_APP_ID, mEventHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mRtcEngine != null) {
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO, Constants.AUDIO_SCENARIO_GAME_STREAMING);
            mRtcEngine.enableAudioVolumeIndication(500, 3, false);
        }
    }

    void joinChannel(String channelId, int userId) {
        if (mRtcEngine != null)
            mRtcEngine.joinChannel(null, channelId, null, userId);
    }

    void setClientRole(int role) {
        if (mRtcEngine != null)
            mRtcEngine.setClientRole(role);
    }


    void setLocalVoiceReverbPreset(int audioReverbOff) {
        if (mRtcEngine != null)
            mRtcEngine.setLocalVoiceReverbPreset(audioReverbOff);
    }

    void setLocalVoiceChanger(int voiceChangerOff) {
        if (mRtcEngine != null)
            mRtcEngine.setLocalVoiceChanger(voiceChangerOff);
    }

    public void muteAllRemoteAudioStreams(boolean muted) {
        if (mRtcEngine != null)
            mRtcEngine.muteAllRemoteAudioStreams(muted);
    }

    void muteLocalAudioStream(boolean muted) {
        if (mRtcEngine != null)
            mRtcEngine.muteLocalAudioStream(muted);
        if (mListener != null)
            mListener.onUserMuteAudio(mUserId, muted);
    }

    public void startAudioMixing(String filePath) {
        if (mRtcEngine != null) {
            mRtcEngine.startAudioMixing(filePath, false, false, 1);
            adjustAudioMixingVolume(SPUtil.getInt(Constant.Channel.VOLUME, 20));
        }
    }

    public void pauseAudioMixing() {
        if (mRtcEngine != null)
            mRtcEngine.pauseAudioMixing();
    }

    public void adjustAudioMixingVolume(int progress) {
        if (mRtcEngine != null)
            mRtcEngine.adjustAudioMixingVolume(progress);
    }

    public void enableInEarMonitoring(boolean b) {
        if (mRtcEngine != null)
            mRtcEngine.enableInEarMonitoring(b);
    }


    void leaveChannel() {
        if (mRtcEngine != null) {
            setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
            mRtcEngine.leaveChannel();
        }
    }


    private IRtcEngineEventHandler mEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            Log.i(TAG, String.format("onJoinChannelSuccess %s %d", channel, uid));

            mUserId = uid;
            if (mListener != null)
                mListener.onJoinChannelSuccess(channel);
        }

        @Override
        public void onClientRoleChanged(int oldRole, int newRole) {
            super.onClientRoleChanged(oldRole, newRole);
            Log.i(TAG, String.format("onClientRoleChanged %d %d", oldRole, newRole));

            if (mListener != null) {
                if (newRole == Constants.CLIENT_ROLE_BROADCASTER)
                    mListener.onUserOnlineStateChanged(mUserId, true);
                else if (newRole == Constants.CLIENT_ROLE_AUDIENCE)
                    mListener.onUserOnlineStateChanged(mUserId, false);
            }
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            Log.i(TAG, String.format("onUserJoined %d", uid));

            if (mListener != null)
                mListener.onUserOnlineStateChanged(uid, true);
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            super.onUserOffline(uid, reason);
            Log.i(TAG, String.format("onUserOffline %d", uid));

            if (mListener != null)
                mListener.onUserOnlineStateChanged(uid, false);
        }

        @Override
        public void onUserMuteAudio(int uid, boolean muted) {
            super.onUserMuteAudio(uid, muted);
            Log.i(TAG, String.format("onUserMuteAudio %d %b", uid, muted));

            if (mListener != null)
                mListener.onUserMuteAudio(uid, muted);
        }

        @Override
        public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
            for (AudioVolumeInfo info : speakers) {
                if (mListener != null)
                    mListener.onAudioVolumeIndication(info.uid, info.volume);
            }
        }

        @Override
        public void onAudioMixingStateChanged(int state, int errorCode) {
            if (mListener != null)
                mListener.onAudioMixingStateChanged(state);
        }

        @Override
        public void onError(int err) {
            super.onError(err);
        }

        @Override
        public void onApiCallExecuted(int error, String api, String result) {

        }
    };

}
