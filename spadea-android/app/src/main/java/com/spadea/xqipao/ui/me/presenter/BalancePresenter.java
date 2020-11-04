package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.spadea.xqipao.data.WxPayModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.BalanceContacter;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class BalancePresenter extends BasePresenter<BalanceContacter.View> implements BalanceContacter.IBalancePre {

    public BalancePresenter(BalanceContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userRecharge(String money, int type) {
        MvpRef.get().showLoadings();
        api.userRecharge(MyApplication.getInstance().getToken(), money, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().userRechargeSuccess(s, type);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
    public void rechargeInfo() {
        MvpRef.get().showLoadings();
        api.rechargeInfo(MyApplication.getInstance().getToken(), new BaseObserver<ArrayList<RechargeInfoModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ArrayList<RechargeInfoModel> s) {
                MvpRef.get().rechargeInfoSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void aliPay(String orderId, int type) {
        MvpRef.get().showLoadings();
        api.aliPay(MyApplication.getInstance().getToken(), MyApplication.getInstance().getUser().getUser_id(), type, orderId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().aliPayment(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void weixinPay(String orderId, int type) {
        MvpRef.get().showLoadings();
        api.wxPay(MyApplication.getInstance().getToken(), MyApplication.getInstance().getUser().getUser_id(), type, orderId, new BaseObserver<WxPayModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(WxPayModel wxPayModel) {
                MvpRef.get().weixinPayment(wxPayModel);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getBalance() {
        api.getBalance(MyApplication.getInstance().getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String data) {
                    MvpRef.get().setBalanceMoney(data);
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
