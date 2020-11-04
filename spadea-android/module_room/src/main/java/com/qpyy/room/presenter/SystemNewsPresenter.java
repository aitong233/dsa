package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.NewsListBean;
import com.qpyy.room.contacts.SystemNewsContacts;


import java.util.List;

import io.reactivex.disposables.Disposable;

public class SystemNewsPresenter extends BasePresenter<SystemNewsContacts.View> implements SystemNewsContacts.ISystemNewsPre {
    public SystemNewsPresenter(SystemNewsContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getList(int page) {
        ApiClient.getInstance().systemNewsList(page, new BaseObserver<List<NewsListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<NewsListBean> listBeans) {
                MvpRef.get().newsList(listBeans);
            }

            @Override
            public void onComplete() {
                MvpRef.get().loadComplete();
            }
        });
    }

    @Override
    public void serviceUser() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().serviceUser(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().serviceSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}