package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.ClosePitModel;
import com.spadea.xqipao.data.RoomPitInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.WheatToolContacts;

import io.reactivex.disposables.Disposable;

public class WheatToolPreswenter extends BasePresenter<WheatToolContacts.View> implements WheatToolContacts.IWheatToolPre {

    public WheatToolPreswenter(WheatToolContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void roomPitInfo(String roomId, String pitNumber) {
        api.roomPitInfo(roomId, pitNumber, new BaseObserver<RoomPitInfo>() {
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
    public void setRoomCardiac(String roomId, int state) {
        api.setRoomCardiac(MyApplication.getInstance().getToken(), roomId, state, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setRoomCardiacSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void clearRoomCardiac(String roomId) {
        api.clearRoomCardiac(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().clearRoomCardiacSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void clearCardiac(String roomId, String pitNumber) {
        api.clearCardiac(MyApplication.getInstance().getToken(), roomId, pitNumber, new BaseObserver<String>() {
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
        api.closePit(MyApplication.getInstance().getToken(), state, pitNumber, roomId, new BaseObserver<ClosePitModel>() {
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
}
