package com.spadea.xqipao.ui.order.presenter;

import android.content.Context;

import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.order.contacts.OrderDetailContacts;

import io.reactivex.disposables.Disposable;

public class OrderDetailPresenter extends BasePresenter<OrderDetailContacts.View> implements OrderDetailContacts.IOrderDetailPre {
    public OrderDetailPresenter(OrderDetailContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getDetail(int orderId, int orderType) {
        api.getOrderDetail(orderId, orderType, new JavaBaseObserver<OrderDetailResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(OrderDetailResp resp) {
                MvpRef.get().orderDetail(resp);
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
                MvpRef.get().accompanyService();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
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
                MvpRef.get().confirmOrder();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
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
                MvpRef.get().accompanyAccept(model);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}