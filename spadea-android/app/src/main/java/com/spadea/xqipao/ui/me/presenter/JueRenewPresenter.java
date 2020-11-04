package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.JueRenewContacts;

import io.reactivex.disposables.Disposable;

public class JueRenewPresenter extends BasePresenter<JueRenewContacts.View> implements JueRenewContacts.IJueRenewPre {

    public JueRenewPresenter(JueRenewContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userNobilityInfo() {
        MvpRef.get().showLoadings();
        api.userNobilityInfo(new BaseObserver<NobilityInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NobilityInfo nobilityInfo) {
                MvpRef.get().userNobilityInfoSuccess(nobilityInfo);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void renewNobility(String day) {
        MvpRef.get().showLoadings();
        api.renewNobility(day, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().renewNobilitySuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
