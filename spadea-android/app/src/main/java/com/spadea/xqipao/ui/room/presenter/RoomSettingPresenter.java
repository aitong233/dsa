package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomSettingContacts;

import io.reactivex.disposables.Disposable;

public class RoomSettingPresenter extends BasePresenter<RoomSettingContacts.View> implements RoomSettingContacts.IRoomSettingPre {

    public RoomSettingPresenter(RoomSettingContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomExtra(String roomId, String password) {
        MvpRef.get().showLoadings();
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
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void editRoom(String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String labelId,String typeId,String greeting,String wheat,String is_password) {
        api.editRoom(MyApplication.getInstance().getToken(), coverPicture, bgPicture, password, playing, roomId, roomName, typeId,labelId,greeting,wheat,is_password, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().editRoomSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
