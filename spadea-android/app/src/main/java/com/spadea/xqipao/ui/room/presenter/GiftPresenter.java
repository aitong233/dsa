package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.GiftContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class GiftPresenter extends BasePresenter<GiftContacts.View> implements GiftContacts.IGiftPre {

    public GiftPresenter(GiftContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void giftWall() {
        api.giftWall(MyApplication.getInstance().getToken(), new BaseObserver<List<GiftModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<GiftModel> giftModels) {
                MvpRef.get().setData(giftModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void userBackPack() {
        api.userBackPack(MyApplication.getInstance().getToken(), new BaseObserver<List<GiftModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<GiftModel> giftModels) {
                MvpRef.get().setData(giftModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
