package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomFragmentContacts;

import io.reactivex.disposables.Disposable;

public class RoomFragmentPresenter extends BasePresenter<RoomFragmentContacts.View> implements RoomFragmentContacts.IRoomFragmentPre {
    public RoomFragmentPresenter(RoomFragmentContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void indexSwitch() {
        api.signSwitch(new BaseObserver<SignSwitchModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SignSwitchModel signSwitchModel) {
                MyApplication.getInstance().labor = signSwitchModel.getLabor() == 1;
                if (signSwitchModel.getChildren() != null) {
                    MvpRef.get().indexSwitch(signSwitchModel.getChildren());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}