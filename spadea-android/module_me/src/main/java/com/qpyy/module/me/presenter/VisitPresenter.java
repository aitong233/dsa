package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.ComeUserResp;
import com.qpyy.module.me.contacts.VisitConacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class VisitPresenter extends BasePresenter<VisitConacts.View> implements VisitConacts.IVisitPre {


    public VisitPresenter(VisitConacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void userVisit(int page) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userVisit(page, new BaseObserver<List<ComeUserResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ComeUserResp> comeUserResps) {
                MvpRef.get().setUserVisit(comeUserResps);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().finishRefresh();
            }
        });
    }
}
