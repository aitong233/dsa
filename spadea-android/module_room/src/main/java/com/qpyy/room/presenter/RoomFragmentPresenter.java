package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.ApplyWheatUsersResp;
import com.qpyy.room.bean.NewsModel;
import com.qpyy.room.contacts.RoomFragmentContacts;

import java.util.Map;

import io.reactivex.disposables.Disposable;

public class RoomFragmentPresenter extends BaseRoomPresenter<RoomFragmentContacts.View> implements RoomFragmentContacts.IRoomFragmentPre {
    public RoomFragmentPresenter(RoomFragmentContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void sendTxtMessage(String user_id, String type, String content, String room_id) {
        ApiClient.getInstance().sendTxtMessage(user_id, type, content, room_id, new BaseObserver<String>() {
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

    public void applyWheatUsers(String roomId) {
        ApiClient.getInstance().applyWheatUsers(roomId, new BaseObserver<ApplyWheatUsersResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ApplyWheatUsersResp applyWheatUsersResp) {
                MvpRef.get().setUserCount(applyWheatUsersResp.getTotal());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void userNews() {
        ApiClient.getInstance().userNews(new BaseObserver<NewsModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NewsModel newsModel) {
                MvpRef.get().userNewsSuccess(newsModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    @Override
    public void roomUpdate(Map<String, String> map, String roomId) {
        map.put("room_id", roomId);
        MvpRef.get().showLoadings();
        ApiClient.getInstance().roomUpdate(map, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void switchMic(String roomId, String pitNumber, int type) {
        ApiClient.getInstance().switchVoice(roomId, pitNumber, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().switchMic(type);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}