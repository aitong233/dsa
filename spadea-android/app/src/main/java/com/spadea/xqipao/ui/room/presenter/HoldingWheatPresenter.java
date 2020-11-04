package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.HoldingWheatContacts;

import io.reactivex.disposables.Disposable;

public class HoldingWheatPresenter extends BasePresenter<HoldingWheatContacts.View> implements HoldingWheatContacts.IHoldingWheatPre {

    public HoldingWheatPresenter(HoldingWheatContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void putOnWheat(String roomId, String userId) {
        api.putOnWheat(roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
