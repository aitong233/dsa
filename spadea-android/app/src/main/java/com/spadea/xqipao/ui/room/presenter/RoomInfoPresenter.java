package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.data.RoomManageModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomInfoContacts;

import io.reactivex.disposables.Disposable;

public class RoomInfoPresenter extends BasePresenter<RoomInfoContacts.View> implements RoomInfoContacts.IRoomInfoPre {

    public RoomInfoPresenter(RoomInfoContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomExtra(String roomId, String password) {
        api.getRoomExtra(MyApplication.getInstance().getToken(), roomId, password, new BaseObserver<RoomExtraModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomExtraModel roomExtraModel) {
                MvpRef.get().setRoomExtraSuccess(roomExtraModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteManager(String roomId, String userId, int postion) {
        MvpRef.get().showLoadings();
        api.deleteManager(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<RoomManageModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomManageModel s) {
                MvpRef.get().delete(0, postion);
                MvpRef.get().deleteManagerSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteForbid(String roomId, String userId, int postion) {
        MvpRef.get().showLoadings();
        api.deleteForbid(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().delete(1, postion);
                MvpRef.get().deleteForbidSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
