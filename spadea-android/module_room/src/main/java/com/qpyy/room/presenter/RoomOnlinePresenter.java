package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.RoomOnlineResp;
import com.qpyy.room.contacts.RoomOnlineContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/25 11:53
 * 描述 describe
 */
public class RoomOnlinePresenter extends BaseRoomPresenter<RoomOnlineContacts.View> implements RoomOnlineContacts.IRoomOnlinePre {
    public RoomOnlinePresenter(RoomOnlineContacts.View view, Context context) {
        super(view, context);
    }

    /**
     * 获取在线列表数据
     */
    @Override
    public void getOnlineList(String roomId, int page) {
        ApiClient.getInstance().getRoomOnlineList(roomId, page, new BaseObserver<RoomOnlineResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomOnlineResp onlineListResps) {
                //获取数据进行业务逻辑
                if (isViewAttach()) {
                    MvpRef.get().setOnlineListView(onlineListResps, page);
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttach()) {
                    MvpRef.get().roomOnlineComplete();
                }
            }
        });

    }
}
