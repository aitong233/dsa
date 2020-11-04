package com.qpyy.room.fragment;

import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.bean.EmChatUserInfo;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.contacts.ChatDialogContacts;
import com.qpyy.room.presenter.ChatDialogPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatDialogFragment extends BaseMvpDialogFragment<ChatDialogPresenter> implements ChatDialogContacts.View {
    @BindView(R2.id.tv_title)
    public TextView mTvTitle;


    public String nickname;
    public String avatar;
    public String userId;

    @Override
    protected ChatDialogPresenter bindPresenter() {
        return new ChatDialogPresenter(this, getActivity());
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setBackgroundDrawableResource(R.drawable.room_bg_top_r40_white);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void initData() {
        LogUtils.e(nickname, avatar, userId);
        MvpPre.getEmChatUserInfo(userId);
    }

    @Override
    protected void initView() {
        mTvTitle.setText(nickname);
    }

    @OnClick(R2.id.iv_back)
    public void onClick(View view) {
        dismiss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_dialog_chat;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ChatDialogFragment");
    }

    @Override
    public void userInfo(EmChatUserInfo userInfo) {
        mTvTitle.setText(userInfo.getNickname());

    }
}
