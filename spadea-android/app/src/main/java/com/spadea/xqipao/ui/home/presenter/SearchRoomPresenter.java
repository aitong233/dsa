package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.xqipao.data.SearchRoomInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.SearchRoomContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class SearchRoomPresenter extends BasePresenter<SearchRoomContacts.View> implements SearchRoomContacts.ISearchRoomPre {

    public SearchRoomPresenter(SearchRoomContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void searchRoom(String keyword) {
        MvpRef.get().showLoadings();
        api.searchRoom(keyword, new BaseObserver<List<SearchRoomInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SearchRoomInfo> searchRoomInfos) {
                MvpRef.get().searchRoomSuccess(searchRoomInfos);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
