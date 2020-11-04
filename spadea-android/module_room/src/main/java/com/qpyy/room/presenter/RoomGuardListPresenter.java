package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.ProtectedRankingListResp;
import com.qpyy.room.contacts.RoomGuardListContact;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.presenter
 * 创建人 王欧
 * 创建时间 2020/4/2 2:29 PM
 * 描述 describe
 */
public class RoomGuardListPresenter extends BasePresenter<RoomGuardListContact.View> implements RoomGuardListContact.RoomGuardListPre {
    public RoomGuardListPresenter(RoomGuardListContact.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getProtectedRankingList(String roomId) {
        ApiClient.getInstance().getProtectedRankingList(roomId, new BaseObserver<ProtectedRankingListResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ProtectedRankingListResp resp) {
                MvpRef.get().protectedRankingList(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
