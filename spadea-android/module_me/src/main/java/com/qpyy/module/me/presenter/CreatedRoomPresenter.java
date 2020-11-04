package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.contacts.CreatedRoomConactos;

import io.reactivex.disposables.Disposable;

public class CreatedRoomPresenter extends BasePresenter<CreatedRoomConactos.View> implements CreatedRoomConactos.ICreatedRoomPre {

    public CreatedRoomPresenter(CreatedRoomConactos.View view, Context context) {
        super(view, context);
    }

    @Override
    public void checkTxt(String content) {
        MvpRef.get().showLoadings();
        api.checkTxt(content,"2", new BaseObserver<CheckTxtResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CheckTxtResp checkTxtResp) {
                MvpRef.get().checkTxtSuccess(checkTxtResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addUserRoom(String roomName, String labelId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addUserRoom(roomName, labelId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addUserRoomSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
