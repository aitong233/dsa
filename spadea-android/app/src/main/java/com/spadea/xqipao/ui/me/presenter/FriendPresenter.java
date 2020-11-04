package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.FriendContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class FriendPresenter extends BasePresenter<FriendContacts.View> implements FriendContacts.IFriendPre {

    public FriendPresenter(FriendContacts.View view, Context context) {
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
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void followList(int page) {
        api.followList(page, new BaseObserver<List<FriendModel>>() {
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
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void fansList(int page) {
        api.fansList(page, new BaseObserver<List<FriendModel>>() {
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
                MvpRef.get().onComplete();
            }
        });
    }
}
