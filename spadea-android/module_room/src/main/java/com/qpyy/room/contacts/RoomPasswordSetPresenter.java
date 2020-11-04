package com.qpyy.room.contacts;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;

import io.reactivex.disposables.Disposable;

public class RoomPasswordSetPresenter extends BasePresenter<RoomPasswordSetContacts.View> implements RoomPasswordSetContacts.IRoomPasswordSetPre {
    public RoomPasswordSetPresenter(RoomPasswordSetContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void setRoomPassword(String roomId, String password) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().updatePassword(roomId, password, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().roomPasswordSettingSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}