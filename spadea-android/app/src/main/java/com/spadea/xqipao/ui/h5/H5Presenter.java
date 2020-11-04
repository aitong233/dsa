package com.spadea.xqipao.ui.h5;

import android.content.Context;

import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;

import io.reactivex.disposables.Disposable;

public class H5Presenter extends BasePresenter<H5Contacts.View> implements H5Contacts.IH5Pre {
    public H5Presenter(H5Contacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void serviceUser() {
        MvpRef.get().showLoadings();
        api.serviceUser(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().serviceUserSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}