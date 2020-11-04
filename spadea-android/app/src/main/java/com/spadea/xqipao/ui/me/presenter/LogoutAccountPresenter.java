package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.LogoutReasonModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.LogoutAccountContacts;

import io.reactivex.disposables.Disposable;

public class LogoutAccountPresenter extends BasePresenter<LogoutAccountContacts.View> implements LogoutAccountContacts.ILogoutAccountPre {
    public LogoutAccountPresenter(LogoutAccountContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getlogoutStatus(String token, String mobile) {
        MvpRef.get().showLoadings();
        api.getlogoutStatus(token, mobile, new BaseObserver<LogoutReasonModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(LogoutReasonModel logoutReasonModel) {
                MvpRef.get().setlogoutStatus(logoutReasonModel);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
