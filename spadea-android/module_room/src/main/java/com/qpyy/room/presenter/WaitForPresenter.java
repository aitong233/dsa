package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.AgreeApplyResp;
import com.qpyy.room.bean.ApplyWheatUsersResp;
import com.qpyy.room.contacts.WaitForContacts;

import io.reactivex.disposables.Disposable;

public class WaitForPresenter extends BasePresenter<WaitForContacts.View> implements WaitForContacts.IWaitForPre {

    public WaitForPresenter(WaitForContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void agreeApply(String id, String roomId, int postion) {
        ApiClient.getInstance().agreeApply(id, roomId, new BaseObserver<AgreeApplyResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(AgreeApplyResp agreeApplyResp) {
                MvpRef.get().agreeApplySuccess(postion);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteApply(String id, String roomId, int postion) {
        ApiClient.getInstance().deleteApply(id, roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().deleteApplySuccess(postion);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void agreeApplyAll(String roomId) {
        ApiClient.getInstance().agreeApplyAll(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().agreeApplyAllSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void applyWheatUsers(String roomId) {
        ApiClient.getInstance().applyWheatUsers(roomId, new BaseObserver<ApplyWheatUsersResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ApplyWheatUsersResp applyWheatUsersResp) {
                MvpRef.get().setApplyWheatUsersData(applyWheatUsersResp.getList());
                MvpRef.get().setUserCount(applyWheatUsersResp.getTotal());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
