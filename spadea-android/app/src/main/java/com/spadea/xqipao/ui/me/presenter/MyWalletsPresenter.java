package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.MyWalletsContacter;

import io.reactivex.disposables.Disposable;

public class MyWalletsPresenter extends BasePresenter<MyWalletsContacter.View> implements MyWalletsContacter.IMyWalletsPre {

    public MyWalletsPresenter(MyWalletsContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBalance() {
        api.getBalance(MyApplication.getInstance().getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String data) {
                    MvpRef.get().setBalanceMoney(data);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
