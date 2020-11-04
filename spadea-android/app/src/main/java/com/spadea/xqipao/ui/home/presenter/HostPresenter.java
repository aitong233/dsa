package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.HostContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class HostPresenter extends BasePresenter<HostContacts.View> implements HostContacts.IHostPre {

    public HostPresenter(HostContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getHostRoom() {
        api.hotRoom(MyApplication.getInstance().getUser().getUser_id(), new BaseObserver<List<RoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomModel> roomModels) {
                MvpRef.get().hostRoomSuccess(roomModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().hostRoomComplete();
            }
        });
    }
}
