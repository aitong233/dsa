package com.qpyy.room.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.contacts.RoomToolContacts;

import io.reactivex.disposables.Disposable;

public class RoomToolPresenter extends BasePresenter<RoomToolContacts.View> implements RoomToolContacts.IRoomToolPre {
    public RoomToolPresenter(RoomToolContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void clearRoomCardiac(String roomId) {
        ApiClient.getInstance().clearRoomCardiac(SpUtils.getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                ToastUtils.show("清除成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }


    //开关心动显示
    @Override
    public void setRoomCardiac(String roomId, int state) {
        ApiClient.getInstance().setRoomCardiac(SpUtils.getToken(), roomId, state, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setRoomCardiacSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}