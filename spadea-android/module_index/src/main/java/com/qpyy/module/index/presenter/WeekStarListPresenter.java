package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.WeekStarResp;
import com.qpyy.module.index.contacts.WeekStarListContacts;
import com.qpyy.module.index.fragment.WeekStarListFragment;

import io.reactivex.disposables.Disposable;

public class WeekStarListPresenter extends BasePresenter<WeekStarListContacts.View> implements WeekStarListContacts.IWeekStarListPre {
    public WeekStarListPresenter(WeekStarListContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getList(String roomId, int type) {
        if (type == WeekStarListFragment.TYPE_ROOM) {
            ApiClient.getInstance().getWeekStarRoom(roomId, new BaseObserver<WeekStarResp.GiftRoomBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(WeekStarResp.GiftRoomBean giftRoomBean) {
                    MvpRef.get().setGifts(giftRoomBean.getGift_info());
                    MvpRef.get().setRoomList(giftRoomBean);
                    MvpRef.get().setMyInfo(null);
                }

                @Override
                public void onComplete() {

                }
            });
        } else if (type == WeekStarListFragment.TYPE_CONTRIBUTE) {
            ApiClient.getInstance().getWeekStarRich(roomId, new BaseObserver<WeekStarResp.GiftRichBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(WeekStarResp.GiftRichBean giftRoomBean) {
                    MvpRef.get().setGifts(giftRoomBean.getGift_info());
                    MvpRef.get().setWealthList(giftRoomBean);
                    MvpRef.get().setMyInfo(giftRoomBean.getMy());
                }

                @Override
                public void onComplete() {

                }
            });
        } else if (type == WeekStarListFragment.TYPE_CHARM) {
            ApiClient.getInstance().getWeekStarCharm(roomId, new BaseObserver<WeekStarResp.GiftCharmBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(WeekStarResp.GiftCharmBean giftRoomBean) {
                    MvpRef.get().setGifts(giftRoomBean.getGift_info());
                    MvpRef.get().setCharmList(giftRoomBean);
                    MvpRef.get().setMyInfo(giftRoomBean.getMy());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}