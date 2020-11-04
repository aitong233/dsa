package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.qpyy.libcommon.utils.SpUtils;
import com.spadea.xqipao.data.UserBankModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.WithdrawalContacter;

import io.reactivex.disposables.Disposable;

public class WithdrawalPresenter extends BasePresenter<WithdrawalContacter.View> implements WithdrawalContacter.IWithdrawalPre {

    public WithdrawalPresenter(WithdrawalContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getUserBank() {
        MvpRef.get().showLoadings();
        api.getAlipayInfo(SpUtils.getToken(), new BaseObserver<UserBankModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserBankModel userBankModel) {
                Log.i("支付宝", "onNext: "+new Gson().toJson(userBankModel));
                if (userBankModel != null) {
                    MvpRef.get().setUserBank(userBankModel);
                    MvpRef.get().setUserMoney(userBankModel.getMoney());
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void userWithdraw(String bankId, String num) {
        Log.i("房间收益", "userWithdraw: 左边");
        MvpRef.get().showLoadings();
        api.userWithdraw(SpUtils.getToken(), num, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().userWithdrawSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
