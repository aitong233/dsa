package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.MyProductsModel;
import com.spadea.xqipao.data.UsingProductsModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.KnapsackItemContacter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class KnapsackItemPresenter extends BasePresenter<KnapsackItemContacter.View> implements KnapsackItemContacter.IKnapsackItemPre {

    public KnapsackItemPresenter(KnapsackItemContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void myProducts(String categoryId) {
        api.myProducts(categoryId, new BaseObserver<List<MyProductsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MyProductsModel> myProductsModel) {
                MvpRef.get().myProductsSuccess(myProductsModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void myUsingProducts(String categoryId) {
        api.myUsingProducts(categoryId, new BaseObserver<UsingProductsModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UsingProductsModel usingProductsModel) {
                MvpRef.get().myUsingProductsSuccess(usingProductsModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void useProduct(String id) {
        MvpRef.get().showLoadings();
        api.useProduct(id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().useProductSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void downProduct(String id) {
        MvpRef.get().showLoadings();
        api.downProduct(id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().downProductSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
