package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.SearchFriendResp;
import com.qpyy.module.me.contacts.SearchFriendConacts;

import io.reactivex.disposables.Disposable;

public class SearchFriendPresenter extends BasePresenter<SearchFriendConacts.View> implements SearchFriendConacts.ISearchFriendPre {

    public SearchFriendPresenter(SearchFriendConacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void search(int type, String keyword) {
        if (type == 0) {
            searchFriend(keyword);
        } else if (type == 1) {
            searchFollow(keyword);
        } else {
            searchFans(keyword);
        }
    }

    @Override
    public void searchFans(String keyword) {
        ApiClient.getInstance().searchFans(keyword, new BaseObserver<SearchFriendResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SearchFriendResp resp) {
                MvpRef.get().setData(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void searchFriend(String keyword) {
        ApiClient.getInstance().searchFriend(keyword, new BaseObserver<SearchFriendResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SearchFriendResp resp) {
                MvpRef.get().setData(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void searchFollow(String keyword) {
        ApiClient.getInstance().searchFollow(keyword, new BaseObserver<SearchFriendResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SearchFriendResp resp) {
                MvpRef.get().setData(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
