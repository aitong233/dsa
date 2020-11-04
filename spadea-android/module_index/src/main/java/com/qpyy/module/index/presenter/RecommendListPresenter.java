package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.contacts.RecommendListContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class RecommendListPresenter extends BasePresenter<RecommendListContacts.View> implements RecommendListContacts.IRecommendListPre {
    public RecommendListPresenter(RecommendListContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomList(int page) {
        ApiClient.getInstance().getRecommendRoomList(page, new BaseObserver<List<RoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomModel> roomModels) {
                MvpRef.get().roomList(roomModels, page);
            }

            @Override
            public void onComplete() {
                MvpRef.get().finishRefreshLoadMore();
            }
        });
    }
}