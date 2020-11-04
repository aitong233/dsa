package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.RoomBgBean;
import com.qpyy.room.contacts.RoomBackgroundContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.presenter
 * 创建人 王欧
 * 创建时间 2020/4/14 4:30 PM
 * 描述 describe
 */
public class RoomBackgroundPresenter extends BasePresenter<RoomBackgroundContacts.View> implements RoomBackgroundContacts.RoomBackgroudPre {
    public RoomBackgroundPresenter(RoomBackgroundContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void setBg(String roomId, String bgPicture) {
        ApiClient.getInstance().editRoomBg(bgPicture, roomId, new BaseObserver<String>() {
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
        ApiClient.getInstance().getRoomBackgroudList(new BaseObserver<List<RoomBgBean>>() {
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
