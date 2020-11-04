package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.UserRoomResp;
import com.qpyy.module.me.contacts.UserRoomContacts;

import io.reactivex.disposables.Disposable;

public class UserRoomPresenter extends BasePresenter<UserRoomContacts.View> implements UserRoomContacts.IUserRoomPre {

    public UserRoomPresenter(UserRoomContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getUserRoom(String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userRoom(userId, new BaseObserver<UserRoomResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserRoomResp userRoomResp) {
                MvpRef.get().setUserRoom(userRoomResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
