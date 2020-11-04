package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.utils.oss.OSSOperUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.RoomAdminModel;
import com.qpyy.room.bean.RoomExtraModel;
import com.qpyy.room.bean.RoomSceneItem;
import com.qpyy.room.contacts.RoomInfoContacts;
import com.qpyy.room.event.RoomGreetingEvent;
import com.qpyy.room.event.RoomPlayingEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/29 11:08
 * 描述 describe
 */
public class RoomInfoPresenter extends BasePresenter<RoomInfoContacts.View> implements RoomInfoContacts.MyRoomMsgPre {

    private String roomId;
    private String password;


    public RoomInfoPresenter(RoomInfoContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomExtra(String roomId, String password) {
        this.roomId = roomId;
        this.password = password;
        ApiClient.getInstance().getRoomExtra(SpUtils.getToken(), roomId, password, new BaseObserver<RoomExtraModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomExtraModel roomExtraModel) {
                MvpRef.get().setRoomExtraSuccess(roomExtraModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteAdmin(String roomId, String userId, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().deleteManager(SpUtils.getToken(), roomId, userId, new BaseObserver<RoomAdminModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomAdminModel roomAdminModel) {
                MvpRef.get().delete(0, position);
                MvpRef.get().deleteAdminSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteBlacklist(String roomId, String userId, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().deleteForbid(SpUtils.getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().delete(1, position);
                MvpRef.get().deleteBlacklistSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void editRoom(String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String labelId, String typeId, String greeting, String wheat, String is_password) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().editRoom(SpUtils.getToken(), coverPicture, bgPicture, password, playing, roomId, roomName, typeId, labelId, greeting, wheat, is_password, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                EventBus.getDefault().post(new RoomPlayingEvent(playing));
                EventBus.getDefault().post(new RoomGreetingEvent(greeting));
                MvpRef.get().editRoomSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void uploadImage(File file, int type) {
        MvpRef.get().showLoadings("上传中...");
        String url = OSSOperUtils.getPath(file, type);
        OSSOperUtils.newInstance().putObjectMethod(url, file.getPath(), new OSSOperUtils.OssCallback() {
            @Override
            public void onSuccess() {
                if (isViewAttach()) {
                    MvpRef.get().disLoadings();
                    String imageUrl = OSSOperUtils.AliYunOSSURLFile + url;
                    MvpRef.get().uploadSuccess(imageUrl);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("cover_picture", imageUrl);
                    roomUpdate(map);
                }
            }

            @Override
            public void onFail() {
                if (isViewAttach()) {
                    MvpRef.get().disLoadings();
                }
            }
        });
    }

    @Override
    public void roomUpdate(Map<String, String> map) {
        map.put("room_id", roomId);
        MvpRef.get().showLoadings();
        ApiClient.getInstance().roomUpdate(map, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                if (map.containsKey("playing")) {
                    EventBus.getDefault().post(new RoomPlayingEvent(map.get("playing")));
                }
                if (map.containsKey("greeting")) {
                    EventBus.getDefault().post(new RoomGreetingEvent(map.get("greeting")));
                }
                MvpRef.get().editRoomSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void soundEffectInfo() {
        ApiClient.getInstance().soundAffectInfo(new BaseObserver<List<RoomSceneItem>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomSceneItem> items) {
                MvpRef.get().soundEffectInfo(items);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void updateSoundEffect(String roomId, RoomSceneItem item, String coverImageUrl, String password, String playCon, String roomName, String welcomeCon, String isPassword) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().updateSoundAffect(roomId, String.valueOf(item.getId()), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                editRoom(coverImageUrl, "", password,
                        playCon, roomId, roomName, "", "", welcomeCon, "", isPassword);//提交修改
                MvpRef.get().updateSoundEffectSuccess(item);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


}
