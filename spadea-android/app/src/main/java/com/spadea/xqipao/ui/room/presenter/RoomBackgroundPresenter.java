package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.xqipao.data.RoomBgBean;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomBackgroudContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.presenter
 * 创建人 王欧
 * 创建时间 2020/4/14 4:30 PM
 * 描述 describe
 */
public class RoomBackgroundPresenter extends BasePresenter<RoomBackgroudContacts.View> implements RoomBackgroudContacts.RoomBackgroudPre {
    public RoomBackgroundPresenter(RoomBackgroudContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void setBg(String roomId, String bgPicture) {
        api.editRoomBg(bgPicture, roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setSuccess(bgPicture);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getBackgroundList() {
        api.getRoomBackgroudList(new BaseObserver<List<RoomBgBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomBgBean> list) {
                MvpRef.get().backgroundList(list);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
