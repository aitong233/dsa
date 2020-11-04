package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.BallResp;
import com.qpyy.room.contacts.GameContactrs;

import io.reactivex.disposables.Disposable;

public class GamePresenter extends BasePresenter<GameContactrs.View> implements GameContactrs.IGamePre {


    public GamePresenter(GameContactrs.View view, Context context) {
        super(view, context);

    }


    @Override
    public void ballStart(String roomId, String pitNumber) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().ballStart(roomId, pitNumber, new BaseObserver<BallResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(BallResp ballResp) {
                MvpRef.get().ballStartSuccess(ballResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void ballThrow(String roomId, String pitNumber) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().ballThrow(roomId, pitNumber, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String ballResp) {
                MvpRef.get().ballThrowSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void ballShow(String roomId, String pitNumber) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().ballShow(roomId, pitNumber, new BaseObserver<BallResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(BallResp ballResp) {
                MvpRef.get().ballShowSuccess(ballResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
