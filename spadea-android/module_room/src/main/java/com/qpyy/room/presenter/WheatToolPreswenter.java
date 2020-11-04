package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.ClosePitModel;
import com.qpyy.room.bean.RoomPitInfo;
import com.qpyy.room.bean.RoomShutUp;
import com.qpyy.room.contacts.WheatToolContacts;

import io.reactivex.disposables.Disposable;

public class WheatToolPreswenter extends BaseRoomPresenter<WheatToolContacts.View> implements WheatToolContacts.IWheatToolPre {

    public WheatToolPreswenter(WheatToolContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void roomPitInfo(String roomId, String pitNumber) {
        ApiClient.getInstance().roomPitInfo(roomId, pitNumber, new BaseObserver<RoomPitInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomPitInfo roomPitInfo) {
                MvpRef.get().setRoomPitInfo(roomPitInfo);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void clearCardiac(String roomId, String pitNumber) {
        ApiClient.getInstance().clearCardiac(SpUtils.getToken(), roomId, pitNumber, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().clearCardiacSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void closePit(String state, String pitNumber, String roomId) {
        ApiClient.getInstance().closePit(SpUtils.getToken(), state, pitNumber, roomId, new BaseObserver<ClosePitModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ClosePitModel s) {
                MvpRef.get().closePitSuccess();

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void closeAllPit(String roomId) {
        ApiClient.getInstance().closeAllPit(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().closePitSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void roomUserShutUp(String roomId, String userId, int type) {
        ApiClient.getInstance().roomUserShutUp(roomId, userId, type, new BaseObserver<RoomShutUp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomShutUp s) {
                MvpRef.get().dismissDialog();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
