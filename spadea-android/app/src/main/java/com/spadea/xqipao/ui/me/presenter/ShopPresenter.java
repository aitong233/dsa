package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ShopContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ShopPresenter extends BasePresenter<ShopContacts.View> implements ShopContacts.IShopPre {

    public ShopPresenter(ShopContacts.View view, Context context) {
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
        api.categories(new BaseObserver<List<CategoriesModel>>() {
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
