package com.qpyy.rtc;

import android.app.Application;

import com.qpyy.libcommon.bean.Config;
import com.qpyy.libcommon.event.RoomOutEvent;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.rtc.agora.MyIRtcEngineEventHandler;
import com.qpyy.rtc.zego.MyIZegoEventHandler;
import com.qpyy.rtc.zego.MyIZegoMediaPlayerEventHandler;

import org.greenrobot.eventbus.EventBus;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.ZegoMediaPlayer;
import im.zego.zegoexpress.callback.IZegoDestroyCompletionCallback;
import im.zego.zegoexpress.callback.IZegoMediaPlayerLoadResourceCallback;
import im.zego.zegoexpress.constants.ZegoANSMode;
import im.zego.zegoexpress.constants.ZegoAudioChannel;
import im.zego.zegoexpress.constants.ZegoAudioCodecID;
import im.zego.zegoexpress.constants.ZegoReverbPreset;
import im.zego.zegoexpress.constants.ZegoScenario;
import im.zego.zegoexpress.constants.ZegoVoiceChangerPreset;
import im.zego.zegoexpress.entity.ZegoAudioConfig;
import im.zego.zegoexpress.entity.ZegoReverbParam;
import im.zego.zegoexpress.entity.ZegoRoomConfig;
import im.zego.zegoexpress.entity.ZegoUser;
import im.zego.zegoexpress.entity.ZegoVoiceChangerParam;
import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;


/**
 * @author xf
 */
public class RtcManager implements Rtc {

    private final static String TAG = "RTC";

    private Long appID = Long.valueOf(BuildConfig.ZEGO_APP_ID);
    private String appSign = BuildConfig.ZEGO_APP_SIGN;
    private String agoraAppId = BuildConfig.AGORA_APP_ID;

    private Application mApplication;
    private static RtcManager instance;
    private static ZegoExpressEngine mZegoExpressEngine;
    private static ZegoMediaPlayer mZegoMediaPlayer;
    private static RtcEventListener mRtcEventListener;
    private int mRtcType;
    private static RtcEngine mRtcEngine;

    public RtcManager(Application application) {
        this.mApplication = application;
    }

    public void addRtcEventListener(RtcEventListener rtcEventListener) {
        mRtcEventListener = rtcEventListener;
    }

    public static RtcManager instance(Application application) {
        if (instance == null) {
            synchronized (RtcManager.class) {
                if (instance == null) {
                    instance = new RtcManager(application);
                }
            }
        }
        return instance;
    }


    public static RtcManager getInstance() {
        return instance;
    }


    @Override
    public void init(int rtcType, int scenariosType, Config config) {
        this.mRtcType = rtcType;
        if (rtcType == RtcConstants.RtcType_AGORA) {
            initAgora(scenariosType);
        } else {
            initZego(scenariosType, config);
        }
    }

    @Override
    public void loginRoom(String roomId, String userId, String userName, String token) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.joinChannel(token, roomId, "Extra Optional Data", Integer.parseInt(userId));
            }
        } else {
            if (mZegoExpressEngine != null) {
                ZegoUser zegoUser = new ZegoUser(userId, userName);
                ZegoRoomConfig zegoRoomConfig = new ZegoRoomConfig();
                zegoRoomConfig.maxMemberCount = 0;
                zegoRoomConfig.isUserStatusNotify = true;
                zegoRoomConfig.token = token;
                if (mZegoExpressEngine != null) {
                    mZegoExpressEngine.loginRoom(roomId, zegoUser, zegoRoomConfig);
                }
            }
        }
    }

    @Override
    public void leaveChannel(String roomId) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.leaveChannel();
            }
        } else {
            if (mZegoExpressEngine != null) {
                mZegoExpressEngine.logoutRoom(roomId);
            }
        }
    }

    @Override
    public void setTone(int type) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                switch (type) {
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE1:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_OLDMAN);
                        break;
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE2:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYBOY);
                        break;
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE3:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYGIRL);
                        break;
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE4:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_ZHUBAJIE);
                        break;
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE5:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_ETHEREAL);
                        break;
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE6:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_HULK);
                        break;
                    default:
                        mRtcEngine.setLocalVoiceChanger(Constants.VOICE_CHANGER_OFF);
                        break;
                }
            }
        } else {
            if (mZegoExpressEngine != null) {
                ZegoVoiceChangerParam zegoVoiceChangerParam = new ZegoVoiceChangerParam();
                ZegoReverbParam zegoReverbParam = new ZegoReverbParam();
                switch (type) {
                    //萝莉
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE1:
                        zegoVoiceChangerParam.pitch = 7f;
                        mZegoExpressEngine.setVoiceChangerParam(zegoVoiceChangerParam);
                        mZegoExpressEngine.setReverbParam(zegoReverbParam);
                        break;
                    //大叔
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE2:
                        zegoVoiceChangerParam.pitch = -3f;
                        mZegoExpressEngine.setVoiceChangerParam(zegoVoiceChangerParam);
                        mZegoExpressEngine.setReverbParam(zegoReverbParam);
                        break;
                    //绿巨人
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE3:
                        zegoVoiceChangerParam.pitch = -5;
                        mZegoExpressEngine.setVoiceChangerParam(zegoVoiceChangerParam);
                        zegoReverbParam.damping = 0.25f;
                        zegoReverbParam.dryWetRatio = 1.41f;
                        zegoReverbParam.reverberance = 0.31f;
                        zegoReverbParam.roomSize = 0.06f;
                        mZegoExpressEngine.setReverbParam(zegoReverbParam);
                        break;
                    //大堂：
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE4:
                        mZegoExpressEngine.setVoiceChangerParam(new ZegoVoiceChangerParam(ZegoVoiceChangerPreset.NONE));
                        mZegoExpressEngine.setReverbParam(new ZegoReverbParam(ZegoReverbPreset.SOFT_ROOM));
                        break;
                    //山谷
                    case RtcConstants.SOUNDEFFECTTYPE_CHANGE_VOICE5:
                        mZegoExpressEngine.setVoiceChangerParam(new ZegoVoiceChangerParam(ZegoVoiceChangerPreset.NONE));
                        mZegoExpressEngine.setReverbParam(new ZegoReverbParam(ZegoReverbPreset.CONCER_HALL));
                        break;
                    default://无声调改变
                        mZegoExpressEngine.setVoiceChangerParam(new ZegoVoiceChangerParam(ZegoVoiceChangerPreset.NONE));
                        mZegoExpressEngine.setReverbParam(new ZegoReverbParam());
                        break;
                }
            }
        }
    }

    @Override
    public void enableHeadphoneMonitor(boolean b) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.enableInEarMonitoring(b);
            }
        } else {
            if (mZegoExpressEngine != null) {
                mZegoExpressEngine.enableHeadphoneMonitor(b);
            }
        }
    }

    @Override
    public void setHeadphoneMonitorVolume(int volume) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.setInEarMonitoringVolume(volume);
            }
        } else {
            if (mZegoExpressEngine != null) {
                mZegoExpressEngine.setHeadphoneMonitorVolume(volume);
            }
        }
    }

    @Override
    public void setAudioMixingVolume(int volume) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.adjustAudioMixingVolume(volume);
            }
        } else {
            if (mZegoMediaPlayer != null) {
                mZegoMediaPlayer.setVolume(volume);
            }
        }
    }

    @Override
    public void startAudioMixing(String url) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.startAudioMixing(url, false, false, 1);
            }
        } else {
            if (mZegoMediaPlayer != null) {
                mZegoMediaPlayer.stop();
                mZegoMediaPlayer.loadResource(url, new IZegoMediaPlayerLoadResourceCallback() {
                    @Override
                    public void onLoadResourceCallback(int i) {
                        LogUtils.e("加载资源", i);
                        mZegoMediaPlayer.start();
                    }
                });
                //打开麦克风则混流
                setAudioMixingVolume(SpUtils.getChannelVolume());
                mZegoMediaPlayer.enableAux(!mZegoExpressEngine.isMicrophoneMuted());
            }
        }
    }

    @Override
    public void stopAudioMixing() {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.stopAudioMixing();
            }
        } else {
            if (mZegoMediaPlayer != null) {
                mZegoMediaPlayer.stop();
            }
        }
    }

    @Override
    public void pauseAudioMixing() {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.pauseAudioMixing();
            }
        } else {
            if (mZegoMediaPlayer != null) {
                mZegoMediaPlayer.pause();
            }
        }
    }

    @Override
    public void resumeAudioMixing() {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.resumeAudioMixing();
            }
        } else {
            if (mZegoMediaPlayer != null) {
                mZegoMediaPlayer.resume();
            }
        }
    }

    @Override
    public void applyWheat(String streamID) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
                mRtcEngine.enableLocalAudio(false);
                mRtcEngine.muteLocalAudioStream(true);
            }
        } else {
            if (mZegoExpressEngine != null) {
                muteLocalAudioStream(true);
                mZegoExpressEngine.startPublishingStream(streamID);
                mZegoExpressEngine.enableCamera(false);
            }
        }
    }

    @Override
    public void downWheat() {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
                mRtcEngine.enableLocalAudio(false);
                mRtcEngine.muteLocalAudioStream(false);
            }
        } else {
            if (mZegoExpressEngine != null) {
                muteLocalAudioStream(true);
                mZegoExpressEngine.stopPublishingStream();
            }
        }
    }

    @Override
    public void muteLocalAudioStream(boolean b) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.muteLocalAudioStream(b);
            }
        } else {
            if (mZegoExpressEngine != null) {
                mZegoExpressEngine.muteMicrophone(b);
                //开关麦克风设置混流状态
                if (mZegoMediaPlayer != null) {
                    mZegoMediaPlayer.enableAux(!b);
                }
            }
        }
    }

    @Override
    public void muteSpeaker(boolean b) {
        if (mRtcType == RtcConstants.RtcType_AGORA) {
            if (mRtcEngine != null) {
                mRtcEngine.muteAllRemoteAudioStreams(b);
            }
        } else {
            if (mZegoExpressEngine != null) {
                mZegoExpressEngine.muteSpeaker(b);
            }
        }
    }

    private void initAgora(int scenariosType) {
        try {
            mRtcEngine = RtcEngine.create(mApplication, agoraAppId, new MyIRtcEngineEventHandler(mRtcEventListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (scenariosType) {
            case RtcConstants.SCENARIOSTYPE_SING:
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO, Constants.AUDIO_SCENARIO_GAME_STREAMING);
                break;
            default:
                mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_STANDARD_STEREO,
                        Constants.AUDIO_SCENARIO_GAME_STREAMING);
                break;
        }
    }

    private void initZego(int scenariosType, Config config) {
        ZegoAudioConfig zegoAudioConfig = new ZegoAudioConfig();
        if (config != null) {
            mZegoExpressEngine = ZegoExpressEngine.createEngine(appID, appSign, BuildConfig.DEBUG, ZegoScenario.getZegoScenario(config.scenario), mApplication, null);
            if (config.codecID != null) {
                zegoAudioConfig.codecID = ZegoAudioCodecID.getZegoAudioCodecID(config.codecID);
            }
            if (config.channel != null) {
                zegoAudioConfig.channel = ZegoAudioChannel.getZegoAudioChannel(config.channel);
            }
            if (config.bitrate != null) {
                zegoAudioConfig.bitrate = config.bitrate;
            }
            if (config.AEC != null) {
                mZegoExpressEngine.enableHeadphoneAEC(config.AEC == 1);
            }
            if (config.ANS != null) {
                mZegoExpressEngine.enableANS(config.ANS == 1);
            }
            if (config.ANSMode != null) {
                mZegoExpressEngine.setANSMode(ZegoANSMode.getZegoANSMode(config.ANSMode));
            }
            if (config.AGC != null) {
                mZegoExpressEngine.enableAGC(config.AGC == 1);
            }
        } else {
            switch (scenariosType) {
                case RtcConstants.SCENARIOSTYPE_TALKING: //语聊房
                    mZegoExpressEngine = ZegoExpressEngine.createEngine(appID, appSign, BuildConfig.DEBUG, ZegoScenario.COMMUNICATION, mApplication, null);
                    zegoAudioConfig.codecID = ZegoAudioCodecID.LOW3;
                    zegoAudioConfig.channel = ZegoAudioChannel.MONO;
                    zegoAudioConfig.bitrate = 48;
                    mZegoExpressEngine.setAudioConfig(zegoAudioConfig);
                    mZegoExpressEngine.enableHeadphoneAEC(false);
                    mZegoExpressEngine.enableANS(true);
                    mZegoExpressEngine.setANSMode(ZegoANSMode.SOFT);
                    mZegoExpressEngine.enableAGC(false);
                    break;
                case RtcConstants.SCENARIOSTYPE_SING://唱歌
                    mZegoExpressEngine = ZegoExpressEngine.createEngine(appID, appSign, BuildConfig.DEBUG, ZegoScenario.GENERAL, mApplication, null);
                    zegoAudioConfig.codecID = ZegoAudioCodecID.LOW3;
                    zegoAudioConfig.bitrate = 64;
                    zegoAudioConfig.channel = ZegoAudioChannel.MONO;
                    mZegoExpressEngine.setAudioConfig(zegoAudioConfig);
                    mZegoExpressEngine.enableHeadphoneAEC(false);
                    mZegoExpressEngine.enableANS(true);
                    mZegoExpressEngine.enableAGC(true);
                    mZegoExpressEngine.enableAEC(true);
                    mZegoExpressEngine.setANSMode(ZegoANSMode.MEDIUM);
                    break;
                default://游戏
                    mZegoExpressEngine = ZegoExpressEngine.createEngine(appID, appSign, BuildConfig.DEBUG, ZegoScenario.GENERAL, mApplication, null);
                    zegoAudioConfig.codecID = ZegoAudioCodecID.LOW3;
                    zegoAudioConfig.channel = ZegoAudioChannel.MONO;
                    zegoAudioConfig.bitrate = 48;
                    mZegoExpressEngine.setAudioConfig(zegoAudioConfig);
                    mZegoExpressEngine.enableHeadphoneAEC(false);
                    mZegoExpressEngine.enableANS(true);
                    mZegoExpressEngine.enableAEC(true);
                    mZegoExpressEngine.setANSMode(ZegoANSMode.MEDIUM);
                    mZegoExpressEngine.enableAGC(true);
                    break;
            }
        }
        enableHeadphoneMonitor(SpUtils.getAuricularBack() == 1);
        setSoundLevelMonitor(true);
        mZegoMediaPlayer = mZegoExpressEngine.createMediaPlayer();
        mZegoExpressEngine.setEventHandler(new MyIZegoEventHandler(mRtcEventListener, mZegoExpressEngine));
        try {
            mZegoMediaPlayer.setEventHandler(new MyIZegoMediaPlayerEventHandler(mRtcEventListener));
        } catch (Exception e) {
            EventBus.getDefault().post(new RoomOutEvent());
            e.printStackTrace();
        }
    }

    public void destroy(final RtcDestroyCallback callback) {
        if (mRtcEngine != null) {
            RtcEngine.destroy();
            if (callback != null) {
                callback.onDestroySuccess();
            }
        }
        if (mZegoExpressEngine != null) {
            if (mZegoMediaPlayer != null) {
                mZegoExpressEngine.destroyMediaPlayer(mZegoMediaPlayer);
            }
            ZegoExpressEngine.destroyEngine(new IZegoDestroyCompletionCallback() {
                @Override
                public void onDestroyCompletion() {
                    if (mRtcEventListener != null) {
                        mRtcEventListener.destroy();
                    }
                    if (callback != null) {
                        callback.onDestroySuccess();
                    }
                }
            });
        }
        if (mRtcEngine == null && mZegoExpressEngine == null) {
            if (callback != null) {
                callback.onDestroySuccess();
            }
        }
        mZegoExpressEngine = null;
    }

    /**
     * 停止/开启声浪检测
     *
     * @param atWheat 停止  true 开启
     */
    public void setSoundLevelMonitor(boolean atWheat) {
        if (mZegoExpressEngine == null) {
            return;
        }
        if (atWheat) {
            mZegoExpressEngine.startSoundLevelMonitor();
            mZegoExpressEngine.startAudioSpectrumMonitor();
        } else {
            mZegoExpressEngine.stopSoundLevelMonitor();
            mZegoExpressEngine.stopAudioSpectrumMonitor();
        }
    }

    /**
     * 上传即构日志
     */
    public void uploadLog() {
        if (mZegoExpressEngine != null) {
            mZegoExpressEngine.uploadLog();
        }
    }

    public void removeRtcEventListener() {
        mRtcEventListener = null;
    }
}
