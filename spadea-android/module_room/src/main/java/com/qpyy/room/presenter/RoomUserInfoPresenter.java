package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.RoomBannedModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.PutOnWheatResp;
import com.qpyy.room.bean.RoomShutUp;
import com.qpyy.room.bean.RoomUserInfoResp;
import com.qpyy.room.contacts.RoomUserInfoContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/8/13 09:16
 * 描述 describe
 */
public class RoomUserInfoPresenter extends BaseRoomPresenter<RoomUserInfoContacts.View> implements RoomUserInfoContacts.IRoomUserInfoPre {
    public RoomUserInfoPresenter(RoomUserInfoContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomUserInfo(String roomId, String userId) {
        ApiClient.getInstance().getRoomUserInfo(roomId, userId, new BaseObserver<RoomUserInfoResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomUserInfoResp roomUserInfo) {
                MvpRef.get().setRoomUserInfoData(roomUserInfo);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                MvpRef.get().roomUserInfoFail();
            }
        });
    }

    @Override
    public void followUser(String userId, int type) {
        ApiClient.getInstance().follow(SpUtils.getToken(), userId, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().followUserSuccess(type);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void downUserWheat(String userId, String roomId, String userName, String pitNumber) {
        ApiClient.getInstance().downUserWheat(SpUtils.getToken(), pitNumber, roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().downUserWheatSuccess(userName, pitNumber);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void kickOut(String userId, String roomId, String userName) {
        ApiClient.getInstance().kickOut(SpUtils.getToken(), userId, roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().kickOutSuccess(userName);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void roomUserShutUp(String roomId, String userId, int type, String userName) {
        ApiClient.getInstance().roomUserShutUp(roomId, userId, type, new BaseObserver<RoomShutUp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomShutUp s) {
                MvpRef.get().roomUserShutUp(type, userName);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setRoomBanned(String roomId, String userId, int type, String userName) {
        ApiClient.getInstance().setRoomBanned(SpUtils.getToken(), roomId, userId, type, new BaseObserver<RoomBannedModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomBannedModel s) {
                MvpRef.get().setRoomBannedSuccess(userName, type);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void sendTxtMessage(String user_id, String type, String content, String room_id) {
        ApiClient.getInstance().sendTxtMessage(user_id, type, content, room_id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().dismissDialog();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void putOnWheat(String roomId, String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().putOnWheat(roomId, userId, new BaseObserver<PutOnWheatResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(PutOnWheatResp s) {
                MvpRef.get().dismissDialog();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    public void clearCardiac(String roomId, String pitNumber) {
        ApiClient.getInstance().clearCardiac(SpUtils.getToken(), roomId, pitNumber, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().dismissDialog();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
