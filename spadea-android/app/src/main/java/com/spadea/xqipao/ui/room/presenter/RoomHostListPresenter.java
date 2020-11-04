package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.AnchorRankingListResp;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.RoomHostListContact;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.presenter
 * 创建人 王欧
 * 创建时间 2020/4/2 4:36 PM
 * 描述 describe
 */
public class RoomHostListPresenter extends BasePresenter<RoomHostListContact.View> implements RoomHostListContact.RoomHostListPre{
    public RoomHostListPresenter(RoomHostListContact.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getAnchorRankingList(String roomId, String type) {
        api.getAnchorRankingList(roomId, type, new BaseObserver<AnchorRankingListResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(AnchorRankingListResp resp) {
                MvpRef.get().anchorRankingList(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void followUser(int position,String userId, int type) {
        api.follow(MyApplication.getInstance().getToken(), userId, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().followUserSuccess(position,type);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
