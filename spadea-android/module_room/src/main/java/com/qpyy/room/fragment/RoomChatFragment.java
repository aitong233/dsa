package com.qpyy.room.fragment;

import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.room.R;
import com.qpyy.room.contacts.RoomChatContacts;
import com.qpyy.room.presenter.RoomChatPresenter;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/7/28 16:46
 * 描述 describe
 */
public class RoomChatFragment extends BaseMvpFragment<RoomChatPresenter> implements RoomChatContacts.View {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_chat_page;
    }

    @Override
    protected RoomChatPresenter bindPresenter() {
        return new RoomChatPresenter(this,getActivity());
    }
}
