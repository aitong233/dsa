package com.spadea.xqipao.ui.order.presenter;

import android.content.Context;

import com.spadea.xqipao.data.AppealingModel;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.OrderMsgResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.chart.contacts.ChatContacts;
import com.spadea.xqipao.ui.order.contacts.OrderNewsContacts;

import io.reactivex.disposables.Disposable;

public class OrderNewsPresenter extends BasePresenter<OrderNewsContacts.View> implements OrderNewsContacts.IOrderNewsPre, ChatContacts.IChatPre {
    public OrderNewsPresenter(OrderNewsContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getList(int page) {
        api.getOrderMsg(page, new JavaBaseObserver<OrderMsgResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(OrderMsgResp orderMsgResp) {
                MvpRef.get().newsList(page, orderMsgResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().endLoading();
            }
        });
    }

    @Override
    public void getLastOrderMsg(String easeName) {
        api.getLastOrderMsg(easeName, new JavaBaseObserver<LastOrderMsg>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(LastOrderMsg msg) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void accompanyService(int orderId) {
        MvpRef.get().showLoadings();
        api.accompanyService(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void agreeRefund(int orderId) {
        MvpRef.get().showLoadings();
        api.agreeRefund(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void disAgreeRefund(int orderId) {
        MvpRef.get().showLoadings();
        api.disagreeRefund(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void accompanyAccept(UpdateOrderModel model) {
        MvpRef.get().showLoadings();
        api.accompanyAcceptService(model, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void bossAcceptService(UpdateOrderModel model) {
        MvpRef.get().showLoadings();
        api.bossAcceptService(model, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void bossConfirmOrder(int orderId) {
        MvpRef.get().showLoadings();
        api.bossConfirmOrder(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void bossRefundOrder(int orderId) {
        MvpRef.get().showLoadings();
        api.boosRefundOrder(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void bossAppealing(int orderId,String userId,String playUserId) {
        MvpRef.get().showLoadings();
        api.bossAppealing(new AppealingModel(orderId,userId,playUserId), new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void bossAgreeRefuseRefund(int orderId) {
        MvpRef.get().showLoadings();
        api.agreeRefuseRefund(orderId, new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().refresh();
            }
        });
    }

    @Override
    public void serviceUser() {
        MvpRef.get().showLoadings();
        api.serviceUser(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().serviceUserSuccess(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}