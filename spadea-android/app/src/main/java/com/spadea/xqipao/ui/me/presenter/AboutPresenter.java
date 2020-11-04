package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.AboutContacts;

import io.reactivex.disposables.Disposable;

public class AboutPresenter extends BasePresenter<AboutContacts.View> implements AboutContacts.IAboutPre {
    public AboutPresenter(AboutContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void appUpdate() {
        MvpRef.get().showLoadings();
        api.checkUpdate(new BaseObserver<AppUpdateModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(AppUpdateModel appUpdateModel) {
                MvpRef.get().appUpdate(appUpdateModel);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
