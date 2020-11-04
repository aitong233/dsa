package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.AllLiveContacts;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class AllLivePresenter extends BasePresenter<AllLiveContacts.View> implements AllLiveContacts.IAllLivePre {
    public AllLivePresenter(AllLiveContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getAllRoom() {
        api.roomList("", new BaseObserver<List<RoomModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomModel> roomModels) {
                MvpRef.get().setAllRoomData(roomModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().allRoomComplete();
            }
        });
    }

    @Override
    public void collection(String roomId) {
        MvpRef.get().showLoadings();
        api.addRoomCollect(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("收藏成功");
                getAllRoom();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void cancelCollection(String roomId) {
        MvpRef.get().showLoadings();
        api.removeFavorite(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("取消收藏成功");
                getAllRoom();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
