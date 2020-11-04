package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.contacts.UserDetailsConacts;

import io.reactivex.disposables.Disposable;

public class UserDetailsPresenter extends BasePresenter<UserDetailsConacts.View> implements UserDetailsConacts.IUserDetailsPre {


    public UserDetailsPresenter(UserDetailsConacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getUserDetails(String userId, String emchatUsername) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userHomePage(userId, emchatUsername, new BaseObserver<UserHomeResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserHomeResp userHomeResp) {
                MvpRef.get().setUserDetails(userHomeResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                MvpRef.get().onFail();
            }
        });
    }

    @Override
    public void follow(String userId, String type) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().follow(userId, Integer.parseInt(type), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().followSuccess("1".equals(type) ? "1" : "0");
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addBlackUser(String blackId, int type) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addBlackUser(blackId, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addBlackUserSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
