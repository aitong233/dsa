package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.ProductsModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.ShopItemContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ShopItemPresenter extends BasePresenter<ShopItemContacts.View> implements ShopItemContacts.IShopItemPre {

    public ShopItemPresenter(ShopItemContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void products(String categoryId) {
        api.products(categoryId, new BaseObserver<List<ProductsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ProductsModel> productsModels) {
                MvpRef.get().productsSuccess(productsModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().productsComplete();
            }
        });
    }

    @Override
    public void buyShop(String productId, String priceId) {
        MvpRef.get().showLoadings();
        api.buyShop("", productId, priceId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().buyShopSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
