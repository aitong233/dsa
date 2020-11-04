package com.qpyy.room.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.CheckTxtResp;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.contacts.PublicScreenEaseChatContacts;

import io.reactivex.disposables.Disposable;

public class PublicScreenEaseChatPresenter extends BasePresenter<PublicScreenEaseChatContacts.View> implements PublicScreenEaseChatContacts.IPublicScreenEaseChatPre {
    public PublicScreenEaseChatPresenter(PublicScreenEaseChatContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void logEmchat(int code, String msg, String toChatUsername) {
        ApiClient.getInstance().logEmchat(code, msg, toChatUsername, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void switchPublicScreen(String room_id, String status) {
            ApiClient.getInstance().switchPublicScreen(room_id, status, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
addDisposable(d);
                }

                @Override
                public void onNext(String s) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    public void sendFace(String roomId, String face_id, String pit, int type) {
        ApiClient.getInstance().sendFace(roomId, face_id, pit, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

}