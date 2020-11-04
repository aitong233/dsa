package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.ProductsModel;
import com.qpyy.room.contacts.ShopItemContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/8/11 10:48
 * 描述 describe
 */
public class ShopItemPresenter extends BaseRoomPresenter<ShopItemContacts.View> implements ShopItemContacts.IShopItemPre{

    public ShopItemPresenter(ShopItemContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void products(String categoryId) {
        ApiClient.getInstance().products(categoryId, new BaseObserver<List<ProductsModel>>() {
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
        ApiClient.getInstance().buyShop("", productId, priceId, new BaseObserver<String>() {
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
