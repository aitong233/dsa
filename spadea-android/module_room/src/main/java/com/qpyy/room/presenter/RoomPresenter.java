package com.qpyy.room.presenter;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.db.DbController;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.libcommon.http.APIException;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.MusicPauseEvent;
import com.qpyy.room.bean.MusicStartEvent;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.contacts.RoomContacts;
import com.qpyy.room.event.SoundLevelEvent;
import com.qpyy.libcommon.bean.Config;
import com.qpyy.rtc.RtcConstants;
import com.qpyy.rtc.RtcEventListener;
import com.qpyy.rtc.RtcManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RoomPresenter extends BasePresenter<RoomContacts.View> implements RoomContacts.IRoomPre, RtcEventListener {

    public RoomPresenter(RoomContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void initRtcManager(Application application) {
        RtcManager.instance(application).addRtcEventListener(this);
    }

    @Override
    public void getRoomIn(String roomId, String password) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().roomGetIn(roomId, password, new BaseObserver<RoomInfoResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomInfoResp resp) {
                LogUtils.e("加入房间成功", resp);
                UserBean userBean = BaseApplication.getIns().getUser();
                Config config = null;
                if (!ObjectUtils.isEmpty(resp.getRoom_info().getSound_effect())) {
                    config = resp.getRoom_info().getSound_effect().getConfig();
                }
                RtcManager.getInstance().init(RtcConstants.RtcType_ZEGO, resp.getRoom_info().getSceneId(), config);
                RtcManager.getInstance().loginRoom(roomId, userBean.getUser_id(), userBean.getNickname(), "");
                if (resp.getUser_info().getPit() != 0) {
                    RtcManager.getInstance().applyWheat(String.format("%s_%s", resp.getRoom_info().getRoom_id(), SpUtils.getUserId()));
                }
                MvpRef.get().roomInfo(resp);
            }


            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (e instanceof APIException) {
                    APIException apiException = (APIException) e;
                    if (apiException.getCode() == 6) {
                        MvpRef.get().showPasswordDialog();
                    } else {
                        MvpRef.get().enterFail();
                    }
                } else {
                    MvpRef.get().enterFail();
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    public void mqttHeartBeat(String roomId) {
        ApiClient.getInstance().mqttHeartBeat(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtils.e("mqtt心跳", s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addFavorite(String roomId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addRoomCollect(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addFavoriteSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void quitRoom(String roomId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().quit(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().quitSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public int getLocalMusicCount() {
        return DbController.getInstance(mContext).queryMUsicCount();
    }

    @Override
    public List<MusicTable> getLovalMusicData() {
        return DbController.getInstance(mContext).queryMusicListAll();
    }

    @Override
    public void onRoomDisConnect() {

    }

    @Override
    public void onRoomConnected() {

    }

    @Override
    public void onRoomconnecting() {

    }


    @Override
    public void onRemoteSoundLevelUpdate(String streamID, int soundLevel) {
        EventBus.getDefault().post(new SoundLevelEvent(streamID, soundLevel));
    }

    @Override
    public void noPlay() {
        LogUtils.e("音乐播放", "noPlay");
    }

    @Override
    public void pausIng() {
        LogUtils.e("音乐播放", "pausIng");
        EventBus.getDefault().post(new MusicPauseEvent());
    }

    @Override
    public void playIng() {
        LogUtils.e("音乐播放", "playIng");

        EventBus.getDefault().post(new MusicStartEvent());
    }

    @Override
    public void playEnded() {
        LogUtils.e("音乐播放", "playEnded");
        getView().playNextMusic();
    }


    /**
     * 销毁成功回调
     */
    @Override
    public void destroy() {
        LogUtils.e("音乐播放", "destroy");
    }

}