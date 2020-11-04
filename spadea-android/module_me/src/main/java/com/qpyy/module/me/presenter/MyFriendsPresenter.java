package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.FriendBean;
import com.qpyy.module.me.contacts.MyFriendsConacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MyFriendsPresenter extends BasePresenter<MyFriendsConacts.View> implements MyFriendsConacts.IMyFriendsPre {

    public MyFriendsPresenter(MyFriendsConacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void friendList(int page) {
        ApiClient.getInstance().getFriendList(page, new BaseObserver<List<FriendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<FriendBean> friendBeans) {
                MvpRef.get().setData(page, friendBeans);
            }

            @Override
            public void onComplete() {
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void followList(int page) {
        ApiClient.getInstance().getFollowList(page, new BaseObserver<List<FriendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<FriendBean> friendBeans) {
                MvpRef.get().setData(page, friendBeans);
            }

            @Override
            public void onComplete() {
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void fansList(int page) {
        ApiClient.getInstance().getFansList(page, new BaseObserver<List<FriendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<FriendBean> friendBeans) {
                MvpRef.get().setData(page, friendBeans);
            }

            @Override
            public void onComplete() {
                MvpRef.get().onComplete();
            }
        });
    }

    public void getData(int type, int page) {
        if (type == 0) {
            friendList(page);
        } else if (type == 1) {
            followList(page);
        } else {
            fansList(page);
        }
    }
}
