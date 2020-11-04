package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomBannedModel;
import com.spadea.xqipao.data.RoomShutUp;
import com.spadea.xqipao.data.RoomUserInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomUserInfoContacts;

import io.reactivex.disposables.Disposable;

public class RoomUserInfoPresenter extends BasePresenter<RoomUserInfoContacts.View> implements RoomUserInfoContacts.IRoomUserInfoPre {

    public RoomUserInfoPresenter(RoomUserInfoContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomUserInfo(String roomId, String userId) {
        api.getRoomUserInfo(roomId, userId, new BaseObserver<RoomUserInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomUserInfo roomUserInfo) {
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
        api.follow(MyApplication.getInstance().getToken(), userId, type, new BaseObserver<String>() {
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
        api.downUserWheat(MyApplication.getInstance().getToken(), pitNumber, roomId, userId, new BaseObserver<String>() {
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
        api.kickOut(MyApplication.getInstance().getToken(), userId, roomId, new BaseObserver<String>() {
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
        api.roomUserShutUp(roomId, userId, type, new BaseObserver<RoomShutUp>() {
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
        api.setRoomBanned(MyApplication.getInstance().getToken(), roomId, userId, type, new BaseObserver<RoomBannedModel>() {
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
}
