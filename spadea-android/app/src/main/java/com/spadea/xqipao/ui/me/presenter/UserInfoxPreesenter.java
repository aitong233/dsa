package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.UserInfoxContacts;

import io.reactivex.disposables.Disposable;

public class UserInfoxPreesenter extends BasePresenter<UserInfoxContacts.View> implements UserInfoxContacts.IUserInfoxPre {

    public UserInfoxPreesenter(UserInfoxContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userInfoData(String userId,String emchatUsername) {
        api.userInfoData(userId,emchatUsername,1, new BaseObserver<UserInfoDataModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserInfoDataModel userInfoDataModel) {
                MvpRef.get().userInfoDataSuccess(userInfoDataModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void followUser(String userId, int type) {
        MvpRef.get().showLoadings();
        api.follow(MyApplication.getInstance().getToken(), userId, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().followUserSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
