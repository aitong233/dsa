package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.xqipao.data.OnlineModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.OnlineContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class OnlinePresenter extends BasePresenter<OnlineContacts.View> implements OnlineContacts.IOnlinePre {

    public OnlinePresenter(OnlineContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void roomOnline(String roomId,int page) {
        api.roomOnline(roomId,page, new BaseObserver<List<OnlineModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<OnlineModel> onlineModels) {
                MvpRef.get().setRoomOnlineData(onlineModels,page);
            }

            @Override
            public void onComplete() {
                MvpRef.get().roomOnlineComplete();
            }
        });
    }
}
