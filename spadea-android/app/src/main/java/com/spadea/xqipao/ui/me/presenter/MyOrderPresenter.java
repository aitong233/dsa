package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.MyOrderContacts;

import io.reactivex.disposables.Disposable;

public class MyOrderPresenter extends BasePresenter<MyOrderContacts.View> implements MyOrderContacts.IMyOrderPre {
    public MyOrderPresenter(MyOrderContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getOrder(int type, int page) {
        if (type == 0) {
            api.getRecvOrders(page, new JavaBaseObserver<OrdersResp>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(OrdersResp ordersResp) {
                    MvpRef.get().ordersResp(page, ordersResp);
                }

                @Override
                public void onComplete() {
                    MvpRef.get().endLoading();
                }
            });
        } else {
            api.getSendOrders(page, new JavaBaseObserver<OrdersResp>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(OrdersResp ordersResp) {
                    MvpRef.get().ordersResp(page, ordersResp);
                }

                @Override
                public void onComplete() {
                    MvpRef.get().endLoading();
                }
            });
        }

    }
}