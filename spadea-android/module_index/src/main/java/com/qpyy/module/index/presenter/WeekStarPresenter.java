package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.LastWeekStarResp;
import com.qpyy.module.index.contacts.WeekStarContacts;

import io.reactivex.disposables.Disposable;


public class WeekStarPresenter extends BasePresenter<WeekStarContacts.View> implements WeekStarContacts.IWeekStarPre {
    public WeekStarPresenter(WeekStarContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getLastList(String roomId, int type) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().getLastWeekStarList(roomId, type, new BaseObserver<LastWeekStarResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(LastWeekStarResp lastWeekStarResp) {
                MvpRef.get().setLastWeekStar(lastWeekStarResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void follow(String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().followUser(userId, 1, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().refreshFollow();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}