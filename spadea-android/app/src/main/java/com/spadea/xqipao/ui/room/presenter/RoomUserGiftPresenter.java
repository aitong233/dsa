package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.xqipao.ui.room.contacts.RoomUserGiftContacts;

import io.reactivex.disposables.Disposable;

public class RoomUserGiftPresenter extends BasePresenter<RoomUserGiftContacts.View> implements RoomUserGiftContacts.IRoomGiftPre {

    public RoomUserGiftPresenter(RoomUserGiftContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBalance() {
        api.getBalance(MyApplication.getInstance().getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setBalanceMoney(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void giveGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type) {
        api.giveGift(MyApplication.getInstance().getToken(), userId, giftId, roomId, pit, num, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo s) {
                MvpRef.get().giveGiftSuccess(giftModel,userId,num,type);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel,int type) {
        api.giveBackGift(MyApplication.getInstance().getToken(), userId, giftId, roomId, pit, num, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo s) {
                MvpRef.get().giveGiftSuccess(giftModel,userId, num,type);

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
