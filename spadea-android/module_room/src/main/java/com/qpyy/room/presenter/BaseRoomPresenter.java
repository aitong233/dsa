package com.qpyy.room.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.http.APIException;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.ApplyWheatUsersResp;
import com.qpyy.room.bean.ApplyWheatWaitResp;
import com.qpyy.room.bean.PutOnWheatResp;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.contacts.BaseRoomContacts;
import com.qpyy.room.event.ApplyWaitEvent;
import com.qpyy.libcommon.event.RoomOutEvent;
import com.qpyy.room.event.UserDownWheatEvent;
import com.qpyy.rtc.RtcManager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

public class BaseRoomPresenter<V extends IView> extends BasePresenter<V> implements BaseRoomContacts.IBaseRoomPre {

    public BaseRoomPresenter(V view, Context context) {
        super(view, context);
    }

    @Override
    public void downWheat(String roomId) {
        ApiClient.getInstance().downWheat(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
//                getRoomInfo(roomId);
                EventBus.getDefault().post(new UserDownWheatEvent());
                RtcManager.getInstance().downWheat();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void applyWheat(String roomId, String pitNumber) {
        ApiClient.getInstance().applyWheat(roomId, pitNumber, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
//                getRoomInfo(roomId);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void applyWheatWait(String roomId, String pitNumber) {
        ApiClient.getInstance().applyWheatWait(roomId, pitNumber, new BaseObserver<ApplyWheatWaitResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ApplyWheatWaitResp applyWheatWaitResp) {
                if (applyWheatWaitResp != null && !applyWheatWaitResp.getState().equals("1")) {
                    EventBus.getDefault().post(new ApplyWaitEvent(true));
                    ToastUtils.show("申请成功");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getRoomInfo(String roomId) {
        ApiClient.getInstance().getRoomInfo(roomId, new BaseObserver<RoomInfoResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomInfoResp roomInfoResp) {
                EventBus.getDefault().post(roomInfoResp);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (e instanceof APIException) {
                    EventBus.getDefault().post(new RoomOutEvent());
                }
            }
        });
    }

    @Override
    public void putOnWheat(String roomId, String userId) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().putOnWheat(roomId, userId, new BaseObserver<PutOnWheatResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(PutOnWheatResp s) {

            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}