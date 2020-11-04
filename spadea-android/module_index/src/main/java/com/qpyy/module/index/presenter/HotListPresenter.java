package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.contacts.HotListContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class HotListPresenter extends BasePresenter<HotListContacts.View> implements HotListContacts.IHotListPre {
    public HotListPresenter(HotListContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomList(String type) {
        ApiClient.getInstance().getHotRoomList(new BaseObserver<List<RoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomModel> roomModels) {
                MvpRef.get().roomList(roomModels);
            }



            @Override
            public void onComplete() {
                MvpRef.get().finishRefreshLoadMore();
            }
        });
    }
}