package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.AddBankContacter;

import io.reactivex.disposables.Disposable;

public class AddBankPresenter extends BasePresenter<AddBankContacter.View> implements AddBankContacter.IAddBankPre {
    public AddBankPresenter(AddBankContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void addBank(String bankNum, String cardholder, String bankName, String mobile, String bankZhi, String cardNumber, String code) {
        MvpRef.get().showLoadings();
        api.addBank(MyApplication.getInstance().getToken(), bankNum, cardholder, 3, bankName, mobile, bankZhi, cardNumber, code, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addBankSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    public void addZFB(String name, String id, String code) {
        MvpRef.get().showLoadings();
        api.bindAlipay(MyApplication.getInstance().getToken(), id,name, code, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addBankSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void editBank(String cardholder, String bank_name, String mobile, String card_number, String id, String bank_num, String bank_zhi,String code) {
        MvpRef.get().showLoadings();
        api.editBank(MyApplication.getInstance().getToken(), cardholder, bank_name, mobile, card_number, id, bank_num, bank_zhi,code, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().editBankSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getPhoneCode(String phoneNumber) {
        MvpRef.get().showLoadings();
        api.sendCode(MyApplication.getInstance().getToken(), phoneNumber, 6, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().getPhoneCodeSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
