package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.LogoutAccountValidationContacts;

import io.reactivex.disposables.Disposable;

public class LogoutAccountValidationPresenter extends BasePresenter<LogoutAccountValidationContacts.View> implements LogoutAccountValidationContacts.ILogoutAccountValidationPre{

    public LogoutAccountValidationPresenter(LogoutAccountValidationContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void sendCode(String phoneNumber, int type) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.sendCode("", phoneNumber, type, new BaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().sendCodeSuccess();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }
    }

    @Override
    public void logoutReason(String token, String mobile, String reason, String code) {
        MvpRef.get().showLoadings();
        api.logoutReason(token,mobile,reason,code,new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().logoutReasonResult();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


}
