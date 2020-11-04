package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.WxPayModel;
import com.qpyy.room.contacts.RechargeContacter;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/8/14 13:58
 * 描述 describe
 */
public class RechargePresenter extends BaseRoomPresenter< RechargeContacter.View> implements RechargeContacter.IRechargePre {

    public RechargePresenter(RechargeContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userRecharge(String money, int type) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userRecharge(SpUtils.getToken(), money, type, new BaseObserver<String>() {
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
        ApiClient.getInstance().rechargeInfo(SpUtils.getToken(), new BaseObserver<ArrayList<RechargeInfoModel>>() {
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
        ApiClient.getInstance().aliPay(SpUtils.getToken(), SpUtils.getUserId(), type, orderId, new BaseObserver<String>() {
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
        ApiClient.getInstance().wxPay(SpUtils.getToken(), SpUtils.getUserId(), type, orderId, new BaseObserver<WxPayModel>() {
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

}
