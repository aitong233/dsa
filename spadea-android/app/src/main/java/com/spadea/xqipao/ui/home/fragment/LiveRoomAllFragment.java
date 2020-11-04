package com.spadea.xqipao.ui.home.fragment;

import android.view.View;

import com.spadea.xqipao.ui.home.presenter.LiveRoomAllPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.contacts.LiveRoomAllContacts;
import com.spadea.yuyin.R;

public class LiveRoomAllFragment extends BaseFragment<LiveRoomAllPresenter> implements LiveRoomAllContacts.View {


    @Override
    protected LiveRoomAllPresenter bindPresenter() {
        return new LiveRoomAllPresenter(this, getContext());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_room_all;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }
}
