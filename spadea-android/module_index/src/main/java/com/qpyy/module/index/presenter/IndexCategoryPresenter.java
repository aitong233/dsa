package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.module.index.bean.BannerModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.RoomTypeModel;
import com.qpyy.module.index.contacts.IndexCategoryContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class IndexCategoryPresenter extends BasePresenter<IndexCategoryContacts.View> implements IndexCategoryContacts.IIndexCategoryPre {
    public IndexCategoryPresenter(IndexCategoryContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getCategories() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().getRoomCategories(new BaseObserver<List<RoomTypeModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomTypeModel> list) {
                MvpRef.get().setCategories(list);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getBanners() {
        ApiClient.getInstance().getBanners(new BaseObserver<List<BannerModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<BannerModel> bannerModels) {
                MvpRef.get().setBanners(bannerModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}