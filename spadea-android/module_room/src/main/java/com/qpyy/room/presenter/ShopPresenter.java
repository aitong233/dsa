package com.qpyy.room.presenter;

import android.content.Context;


import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.CategoriesModel;
import com.qpyy.room.contacts.ShopContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ShopPresenter extends BaseRoomPresenter<ShopContacts.View> implements ShopContacts.IShopPre {

    public ShopPresenter(ShopContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBalance() {

        ApiClient.getInstance().getBalance(SpUtils.getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setBalanceMoney(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void categories() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().categories(new BaseObserver<List<CategoriesModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<CategoriesModel> categoriesModels) {
                MvpRef.get().categoriesSuccess(categoriesModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
