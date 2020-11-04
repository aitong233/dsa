package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.EvaluateModel;
import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;
import com.spadea.xqipao.ui.me.contacter.OderScoreContacts;

import io.reactivex.disposables.Disposable;

public class OderScorePresenter extends BasePresenter<OderScoreContacts.View> implements OderScoreContacts.IOderScorePre {
    public OderScorePresenter(OderScoreContacts.View view, Context context) {
        super(view, context);
    }

    public void getDetail(int orderId) {
        api.getOrderEvaluateDetail(orderId, new JavaBaseObserver<OrderDetailResp>() {
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
    public void evaluate(EvaluateModel model, int type) {
        MvpRef.get().showLoadings();
        if (type == MyOrderAdapter.TYPE_RECV) {
            api.evaluateBoss(model, new JavaBaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().evaluateComplete();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        } else {
            api.evaluateAccompany(model, new JavaBaseObserver<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(String s) {
                    MvpRef.get().evaluateComplete();
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }

    }
}