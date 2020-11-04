package com.spadea.xqipao.ui.chart.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module_news.api.ApiClient;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.spadea.xqipao.ui.chart.contacts.ChatMoreContacts;

import io.reactivex.disposables.Disposable;

public class ChatMorePresenter extends BasePresenter<ChatMoreContacts.View> implements ChatMoreContacts.IChatMorePre {
    public ChatMorePresenter(ChatMoreContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void addUser2BlackList(String userId, String easeUserName) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addUserBlack(userId, 1, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                try {
                    EMClient.getInstance().contactManager().addUserToBlackList(easeUserName, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ToastUtils.show("添加黑名单成功");
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getEmChatUserInfo(String emChatName) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().getInfoByEmChat(emChatName, new BaseObserver<EmChatUserInfo>() {
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
                MvpRef.get().disLoadings();
            }
        });
    }
}