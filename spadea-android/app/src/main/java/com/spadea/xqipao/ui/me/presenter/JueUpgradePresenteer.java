package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.NobilityModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.JueUpgradeContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class JueUpgradePresenteer extends BasePresenter<JueUpgradeContacts.View> implements JueUpgradeContacts.IJueUpgradePre {

    public JueUpgradePresenteer(JueUpgradeContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void nobility() {
        MvpRef.get().showLoadings();
        api.nobility(new BaseObserver<List<NobilityModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<NobilityModel> nobilityModels) {
                MvpRef.get().nobilitySuccess(nobilityModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
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
    public void buyNobility(String nobilityId) {
        MvpRef.get().showLoadings();
        api.buyNobility(nobilityId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().buyNobilitySuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
