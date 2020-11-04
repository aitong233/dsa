package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.JueContacts;

import io.reactivex.disposables.Disposable;

public class JuePresenter extends BasePresenter<JueContacts.View> implements JueContacts.IJuyePre {

    public JuePresenter(JueContacts.View view, Context context) {
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
}
