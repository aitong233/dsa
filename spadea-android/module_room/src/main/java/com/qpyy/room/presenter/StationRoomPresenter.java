package com.qpyy.room.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.FmApplyWheatResp;
import com.qpyy.room.bean.ProtectedItemBean;
import com.qpyy.room.contacts.StationRoomContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class StationRoomPresenter extends BaseRoomPresenter<StationRoomContacts.View> implements StationRoomContacts.IStationRoomPre {
    public StationRoomPresenter(StationRoomContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void applyWheatFm(String roomId, String pitNumber) {
        ApiClient.getInstance().applyWheatFm(roomId, pitNumber, new BaseObserver<FmApplyWheatResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(FmApplyWheatResp resp) {
                if (resp.getState() == 1) {
//                    getRoomInfo(roomId);
                } else {
                    getProtectedList(resp.getType());
                }
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void getProtectedList(int type) {
        ApiClient.getInstance().getProtectedList(new BaseObserver<List<ProtectedItemBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ProtectedItemBean> list) {
                MvpRef.get().protectedList(list, type);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void openFmProtected(String roomId, String type, String userId) {
        ApiClient.getInstance().openFmProtected(roomId, type, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.show("开通成功");
                MvpRef.get().dismissOpenGuardDialog();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}