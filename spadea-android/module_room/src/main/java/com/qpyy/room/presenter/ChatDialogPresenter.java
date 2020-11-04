package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.EmChatUserInfo;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.contacts.ChatDialogContacts;

import io.reactivex.disposables.Disposable;

public class ChatDialogPresenter extends BasePresenter<ChatDialogContacts.View> implements ChatDialogContacts.IChatDialogPre {
    public ChatDialogPresenter(ChatDialogContacts.View view, Context context) {
        super(view, context);
    }

    public void getEmChatUserInfo(String emChatUserName) {
        getEmChatUserInfo(emChatUserName, false);
    }

    public void getEmChatUserInfo(String emChatUserName, boolean isGift) {
        api.getInfoByEmChat(emChatUserName, new BaseObserver<EmChatUserInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(EmChatUserInfo userInfo) {
                MvpRef.get().userInfo(userInfo);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}