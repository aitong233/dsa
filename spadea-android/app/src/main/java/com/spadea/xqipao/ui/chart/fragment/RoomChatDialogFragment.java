package com.spadea.xqipao.ui.chart.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyphenate.easeui.domain.EaseUser;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.qpyy.room.event.RefreshConversationListEvent;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.FragmentUtils;
import com.spadea.xqipao.data.EaseMsgErrorEvent;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.even.PullOrderMsgEvent;
import com.spadea.xqipao.echart.EChartHelper;
import com.spadea.xqipao.ui.chart.presenter.RoomDialogChatPresenter;
import com.spadea.xqipao.ui.chart.runtimepermissions.PermissionsManager;
import com.spadea.xqipao.ui.chart.contacts.RoomDialogChatContacts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.chart.fragment
 * 创建人 黄强
 * 创建时间 2020/8/20 12:18
 * 描述 describe
 */
@Route(path = ARouteConstants.ROOM_CHART)
public class RoomChatDialogFragment extends BaseMvpDialogFragment<RoomDialogChatPresenter> implements RoomDialogChatContacts.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.container)
    FrameLayout container;
    private RoomEaseChatFragment chatFragment;

    @Autowired
    public String nickname;
    @Autowired
    public String avatar;
    @Autowired
    public String userId;
    private LastOrderMsg mLastOrderMsg;

    public RoomChatDialogFragment() {
    }


    @Override
    protected void initData() {
        MvpPre.getEmChatUserInfo(userId);
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected RoomDialogChatPresenter bindPresenter() {
        return new RoomDialogChatPresenter(this, getContext());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        chatFragment = new RoomChatFragment();
        chatFragment.setArguments(arguments);
        FragmentUtils.add(getChildFragmentManager(), chatFragment, R.id.container);
    }

    @Override
    protected void initView() {
        tvTitle.setText(nickname);
        if (!TextUtils.isEmpty(nickname)) {
            saveEaseUser();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_em_activity_chat;
    }

    private void saveEaseUser() {
        EaseUser easeUser = new EaseUser(userId.toLowerCase());
        easeUser.setAvatar(avatar);
        easeUser.setNickname(nickname);
        EChartHelper.getInstance().saveContact(easeUser);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    public void showLoadings() {
//        showLoading();
    }

    @Override
    public void disLoadings() {
//        disLoading();
    }


    @Override
    public void lastOrderMsg(LastOrderMsg msg) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msgSendError(EaseMsgErrorEvent event) {
        MvpPre.getEmChatUserInfo(userId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void pullOrderMsg(PullOrderMsgEvent event) {
//        MvpPre.getLastOrderMsg(toChatUsername);
    }

    @Override
    public void userInfo(EmChatUserInfo userInfo) {
        avatar = userInfo.getHead_picture();
        nickname = userInfo.getNickname();
        tvTitle.setText(nickname);
        saveEaseUser();
        if ("1".equals(userInfo.getIs_black())) {
            if (chatFragment != null) {
                chatFragment.hideExtendMenu();
            }
        }
        if (chatFragment != null) {
            chatFragment.setUserId(userInfo.getUser_id());
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBus.getDefault().post(new RefreshConversationListEvent());

    }

    @Override
    public void showGiftDialog(String userId) {
        ChatGiftFragment.newInstance(userId).show(getFragmentManager(), "ChatGiftFragment");
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.dismiss();
                break;
        }
    }

}
