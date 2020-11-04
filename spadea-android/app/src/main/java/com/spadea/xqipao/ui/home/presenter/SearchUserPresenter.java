package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.xqipao.data.SearchUserInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.SearchUserContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SearchUserPresenter extends BasePresenter<SearchUserContacts.View> implements SearchUserContacts.ISearchUserPre {

    public SearchUserPresenter(SearchUserContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void searchUser(String keyword) {
        if (isViewAttach()) {
            MvpRef.get().showLoadings();
            api.searchUser(keyword, new BaseObserver<List<SearchUserInfo>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(List<SearchUserInfo> searchUserInfos) {
                    MvpRef.get().searchUserSuccess(searchUserInfos);
                }

                @Override
                public void onComplete() {
                    MvpRef.get().disLoadings();
                }
            });
        }
    }
}
