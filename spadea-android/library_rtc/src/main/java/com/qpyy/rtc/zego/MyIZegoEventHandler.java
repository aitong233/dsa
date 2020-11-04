package com.qpyy.rtc.zego;

import android.util.Log;

import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.rtc.RtcEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.constants.ZegoRoomState;
import im.zego.zegoexpress.constants.ZegoUpdateType;
import im.zego.zegoexpress.entity.ZegoStream;

public class MyIZegoEventHandler extends IZegoEventHandler {

    private RtcEventListener mRtcEventListener;
    private ZegoExpressEngine mZegoExpressEngine;

    public MyIZegoEventHandler(RtcEventListener rtcEventListener, ZegoExpressEngine zegoExpressEngine) {
        this.mRtcEventListener = rtcEventListener;
        this.mZegoExpressEngine = zegoExpressEngine;
    }


    @Override
    public void onRoomStateUpdate(String roomID, ZegoRoomState state, int errorCode, JSONObject extendedData) {

        try {
            if (mRtcEventListener != null) {
                switch (state) {
                    case CONNECTED:
                        mRtcEventListener.onRoomConnected();
                        break;
                    case DISCONNECTED:
                        mRtcEventListener.onRoomDisConnect();
                        break;
                    case CONNECTING:
                        mRtcEventListener.onRoomconnecting();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //            相同房间内其他用户推的流增加或减少的通知
    @Override
    public void onRoomStreamUpdate(String roomID, ZegoUpdateType updateType, ArrayList<ZegoStream> streamList) {
        try {
            if (mZegoExpressEngine != null) {
                for (ZegoStream zegoStream : streamList) {
                    switch (updateType) {
                        case DELETE:
                            mZegoExpressEngine.stopPlayingStream(zegoStream.streamID);
                            break;
                        case ADD:
                            mZegoExpressEngine.startPlayingStream(zegoStream.streamID, null);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //远端拉流音频声浪回调
    @Override
    public void onRemoteSoundLevelUpdate(HashMap<String, Float> soundLevels) {
        try {
            if (mRtcEventListener != null) {
                for (String key : soundLevels.keySet()) {
                    String[] split = key.split("_");
                    mRtcEventListener.onRemoteSoundLevelUpdate(split[1], (int) Float.parseFloat(String.valueOf(soundLevels.get(key))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //本地采集音频声浪回调
    @Override
    public void onCapturedSoundLevelUpdate(float soundLevel) {
        try {
            if (mRtcEventListener != null) {
                UserBean user = BaseApplication.getIns().getUser();
                mRtcEventListener.onRemoteSoundLevelUpdate(user.getUser_id(), (int) soundLevel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
