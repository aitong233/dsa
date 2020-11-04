package com.spadea.xqipao.ui.chart.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module_news.api.ApiClient;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.spadea.xqipao.data.AppealingModel;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.chart.contacts.RoomDialogChatContacts;

import io.reactivex.disposables.Disposable;

public class RoomDialogChatPresenter extends BasePresenter<RoomDialogChatContacts.View> implements RoomDialogChatContacts.IChatPre, IPresenter {


    public RoomDialogChatPresenter(RoomDialogChatContacts.View view, Context context) {
        super(view, context);
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
                MvpRef.get().lastOrderMsg(msg);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
            }
        });
    }

    @Override
    public void bossAppealing(int orderId, String userId, String playUserId) {
        MvpRef.get().showLoadings();
        api.bossAppealing(new AppealingModel(orderId, userId, playUserId), new JavaBaseObserver<String>() {
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
                MvpRef.get().pullOrderMsg(null);
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
                MvpRef.get().pullOrderMsg(null);
            }
        });
    }

    public void getEmChatUserInfo(String emChatUserName) {
        getEmChatUserInfo(emChatUserName, false);
    }

    public void getEmChatUserInfo(String emChatUserName, boolean isGift) {
        ApiClient.getInstance().getInfoByEmChat(emChatUserName, new BaseObserver<EmChatUserInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(EmChatUserInfo userInfo) {
                MvpRef.get().userInfo(userInfo);
                if (isGift && !"1".equals(userInfo.getIs_black())) {
                    MvpRef.get().showGiftDialog(userInfo.getUser_id());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}