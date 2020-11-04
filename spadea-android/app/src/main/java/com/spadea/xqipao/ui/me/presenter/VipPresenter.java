package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.VipInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.VipContacts;

import io.reactivex.disposables.Disposable;

public class VipPresenter extends BasePresenter<VipContacts.View> implements VipContacts.IVipPre {

    public VipPresenter(VipContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void vipInfo() {
        MvpRef.get().showLoadings();
        api.vipInfo(new BaseObserver<VipInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(VipInfo vipInfo) {
                MvpRef.get().vipInfoSuccess(vipInfo);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
