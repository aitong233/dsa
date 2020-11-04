package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.PitCountDownBean;
import com.qpyy.room.contacts.PitCountDownContacts;

import io.reactivex.disposables.Disposable;

public class PitCountDownPresenter extends BasePresenter<PitCountDownContacts.View> implements PitCountDownContacts.IPitCountDownPre {
    public PitCountDownPresenter(PitCountDownContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void pitCountDown(String roomId, String pitNumber, String time) {
        ApiClient.getInstance().pitCountDown(roomId, pitNumber, time, new BaseObserver<PitCountDownBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(PitCountDownBean pitCountDownBean) {
                MvpRef.get().pitCountDown(roomId,pitNumber,time);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}