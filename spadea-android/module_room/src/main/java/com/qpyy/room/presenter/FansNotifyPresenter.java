package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.FansNotifyInfo;
import com.qpyy.room.contacts.FansNotifyContacts;

import io.reactivex.disposables.Disposable;

public class FansNotifyPresenter extends BasePresenter<FansNotifyContacts.View> implements FansNotifyContacts.IFansNotifyPre {
    public FansNotifyPresenter(FansNotifyContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void fansNotifyInfo() {
        ApiClient.getInstance().fansNotifyInfo(new BaseObserver<FansNotifyInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(FansNotifyInfo info) {
                MvpRef.get().fansNotifyInfo(info);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void fansNotify(String roomId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().fansNotify(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().success();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}