package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.DefaultLiveContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class DefaultLivePresenter extends BasePresenter<DefaultLiveContacts.View> implements DefaultLiveContacts.IDefaultLivePre {


    public DefaultLivePresenter(DefaultLiveContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomList(String type) {
        api.roomList(type, new BaseObserver<List<RoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomModel> roomModels) {
                MvpRef.get().roomListtSuccess(roomModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().roomListComplete();
            }
        });
    }
}
