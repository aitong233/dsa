package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomRankingContacts;

import io.reactivex.disposables.Disposable;

public class RoomRankingPresenter extends BasePresenter<RoomRankingContacts.View> implements RoomRankingContacts.IRoomRankingPre {
    public RoomRankingPresenter(RoomRankingContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getCharmList(String roomId, int type) {
        api.getCharmList(MyApplication.getInstance().getToken(), type, roomId, new BaseObserver<CharmModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmModel charmModel) {
                MvpRef.get().setData(charmModel.getLists());
                MvpRef.get().setUserData(charmModel.getMy());
            }

            @Override
            public void onComplete() {
                MvpRef.get().onComplete();
            }
        });
    }

    @Override
    public void getWealthList(String roomId, int type) {
        api.getWealthList(MyApplication.getInstance().getToken(), type, roomId, new BaseObserver<CharmModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmModel charmModel) {
                MvpRef.get().setData(charmModel.getLists());
                MvpRef.get().setUserData(charmModel.getMy());
            }

            @Override
            public void onComplete() {
                MvpRef.get().onComplete();
            }
        });
    }
}
