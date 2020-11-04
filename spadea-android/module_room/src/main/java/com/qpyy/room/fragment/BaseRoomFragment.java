package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.contacts.BaseRoomContacts;
import com.qpyy.room.presenter.BaseRoomPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseRoomFragment<P extends BaseRoomPresenter> extends BaseMvpFragment<P> implements BaseRoomContacts.View {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        unRegisterWheatViews();
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    protected void initView() {
        registerWheatViews();
    }

    /**
     * 房间信息
     *
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomInfo(RoomInfoResp resp) {
        roomInfoUpdate(resp);
    }

    public abstract void roomInfoUpdate(RoomInfoResp resp);

    public abstract void registerWheatViews();

    public abstract void unRegisterWheatViews();
}