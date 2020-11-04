package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.contacts.RoomGuideContacts;

import io.reactivex.disposables.Disposable;

public class RoomGuidePresenter extends BasePresenter<RoomGuideContacts.View> implements RoomGuideContacts.IRoomGuidePre {
    public RoomGuidePresenter(RoomGuideContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void completeGuide(String roomId) {
        ApiClient.getInstance().roomGuide(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().success();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}