package com.spadea.xqipao.ui.order.presenter;

import android.content.Context;
import android.view.View;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.AddOrderModel;
import com.spadea.xqipao.data.OrderPayModel;
import com.spadea.xqipao.data.OrderSkillSelectItem;
import com.spadea.xqipao.data.UserSkillInfo;
import com.spadea.xqipao.data.VerifyOrderTimeModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.data.api.ResultCode;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.order.contacts.ConfirmOrderContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ConfirmOrderPresenter extends BasePresenter<ConfirmOrderContacts.View> implements ConfirmOrderContacts.IConfirmOrderPre {
    public ConfirmOrderPresenter(ConfirmOrderContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getSkillList(String userId) {
        api.getOrderSkillList(userId, new JavaBaseObserver<List<OrderSkillSelectItem>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<OrderSkillSelectItem> list) {
                MvpRef.get().skillList(list);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getSkillInfo(String userId, int id) {
        api.getUserSkillInfo(userId, id, new JavaBaseObserver<UserSkillInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserSkillInfo skillInfo) {
                MvpRef.get().skillInfo(skillInfo);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void createOrder(AddOrderModel model, View view) {
        view.setEnabled(false);
        MvpRef.get().showLoadings();
        api.addOrder(model, new JavaBaseObserver<String>(ResultCode.ORDER_NO_BALANCE.code) {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String resp) {
                MvpRef.get().paySuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                if (view != null) {
                    view.setEnabled(true);
                }
            }

            @Override
            public void onErrorCode(int code) {
                super.onErrorCode(code);
                getBalance();
            }
        });
    }

    @Override
    public void verifyTime(VerifyOrderTimeModel model, String timeStr) {
        api.verifyOrderTime(model, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().timeVerified(model, timeStr);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pay(OrderPayModel model) {
        MvpRef.get().showLoadings();
        api.orderPay(model, new JavaBaseObserver<String>(ResultCode.ORDER_NO_BALANCE.code) {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().paySuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }

            @Override
            public void onErrorCode(int code) {
                super.onErrorCode(code);
                getBalance();
            }
        });
    }

    public void getBalance() {
        api.getBalance(MyApplication.getInstance().getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().showNoBalance(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }


}