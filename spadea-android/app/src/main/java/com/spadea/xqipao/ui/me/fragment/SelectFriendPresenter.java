package com.spadea.xqipao.ui.me.fragment;

import android.content.Context;

import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.SelectFriendContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SelectFriendPresenter extends BasePresenter<SelectFriendContacts.View> implements SelectFriendContacts.ISelectFriendPre {
    public SelectFriendPresenter(SelectFriendContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void friendList(int page) {
        api.friendList(page, new BaseObserver<List<FriendModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<FriendModel> friendModels) {
                MvpRef.get().setData(page, friendModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().finishLoading();
            }
        });
    }

    @Override
    public void buyShop(String friendId, String productId, String priceId) {
        MvpRef.get().showLoadings();
        api.buyShop(friendId, productId, priceId, new BaseObserver<String>() {
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
