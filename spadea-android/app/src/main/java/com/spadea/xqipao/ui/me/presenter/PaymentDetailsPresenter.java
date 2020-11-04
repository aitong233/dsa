package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CashTypeModel;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.PaymentDetailsContacter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class PaymentDetailsPresenter extends BasePresenter<PaymentDetailsContacter.View> implements PaymentDetailsContacter.IPaymentDetailsPre {


    private int p = 1;

    public PaymentDetailsPresenter(PaymentDetailsContacter.View view, Context context) {
        super(view, context);
    }




    @Override
    public void getCashLog(int type, boolean x) {
        if (x) {
            p = 1;
        } else {
            p++;
        }
        MvpRef.get().showLoadings();
        api.getCashLog(MyApplication.getInstance().getToken(), p, type, new BaseObserver<List<EarningsModel.EarningInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<EarningsModel.EarningInfo> earningInfos) {
                MvpRef.get().getCashLogSuccess(earningInfos, x);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void cashType() {
        api.cashType(new BaseObserver<List<CashTypeModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<CashTypeModel> cashTypeModels) {
                MvpRef.get().cashTypeSuccess(cashTypeModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
