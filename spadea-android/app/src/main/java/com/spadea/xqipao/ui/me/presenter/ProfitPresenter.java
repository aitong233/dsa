package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.xqipao.data.ProfitModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ProfitContacter;

import io.reactivex.disposables.Disposable;

public class ProfitPresenter extends BasePresenter<ProfitContacter.View> implements ProfitContacter.IProfitPre {


    public ProfitPresenter(ProfitContacter.View view, Context context) {
        super(view, context);
    }


    @Override
    public void getEarnings() {
        MvpRef.get().showLoadings();
        api.getEarnings(MyApplication.getInstance().getToken(), new BaseObserver<EarningsModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(EarningsModel earningsModel) {
                if (earningsModel != null) {
                    MvpRef.get().setEarnings(earningsModel.getEarnings());
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void convertEarnings(String number, String password) {
        MvpRef.get().showLoadings();
        api.convertEarnings(MyApplication.getInstance().getToken(), number, password, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().convertEarningsSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    /**
     * 房间佣金兑换
     *
     * @param number
     * @param password
     */
    @Override
    public void exchangeRoomEarnings(String number, String password) {
        api.exchangeRoomEarnings(number, password, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().convertEarningsSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getUserProfit() {
        api.userProfit(new BaseObserver<ProfitModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ProfitModel profitModel) {
                MvpRef.get().userProfit(profitModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getRoomProfit() {
        api.roomProfit(new BaseObserver<ProfitModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ProfitModel profitModel) {
                MvpRef.get().roomProfit(profitModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 房间收益提现
     *
     * @param password
     * @param number
     */
    @Override
    public void applyRoomProfit(String password, String number) {
        MvpRef.get().showLoadings();
        api.applyRoomProfit(password, number, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().applyRoomProfitSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }


}
