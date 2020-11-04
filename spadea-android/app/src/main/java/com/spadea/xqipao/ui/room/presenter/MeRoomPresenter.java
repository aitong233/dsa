package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.xqipao.data.ManageRoomModel;
import com.spadea.xqipao.data.MyManageRoomModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.MeRoomContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MeRoomPresenter extends BasePresenter<MeRoomContacts.View> implements MeRoomContacts.IMeRoomPre {


    public MeRoomPresenter(MeRoomContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void manageRoom(int page) {
        api.manageRoom(page, new BaseObserver<MyManageRoomModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(MyManageRoomModel manageRoomModels) {
                MvpRef.get().MyRoomDataSuccess(manageRoomModels);
                MvpRef.get().roomDataSuccess(manageRoomModels.getManager());
            }

            @Override
            public void onComplete() {
                MvpRef.get().roomDataComplete();
            }
        });
    }

    @Override
    public void collectRoom(int page) {
        api.collectRoom(page, new BaseObserver<List<ManageRoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ManageRoomModel> manageRoomModels) {
                MvpRef.get().roomDataSuccess(manageRoomModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().roomDataComplete();
            }
        });
    }

    @Override
    public void cancelRoomManager(String roomId,int position) {
        api.cancelRoomManager(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().roomManagerCanceled(position);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
