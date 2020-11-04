package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.GiftBean;
import com.qpyy.module.me.contacts.UserGiftWallConacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class UserGiftWallPresenter extends BasePresenter<UserGiftWallConacts.View> implements UserGiftWallConacts.IUserGiftWallPre {

    public UserGiftWallPresenter(UserGiftWallConacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void giftWall(String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().giftWall(userId, new BaseObserver<List<GiftBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<GiftBean> giftBeans) {
                MvpRef.get().setGiftWall(giftBeans);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().finishRefresh();
            }
        });
    }
}
