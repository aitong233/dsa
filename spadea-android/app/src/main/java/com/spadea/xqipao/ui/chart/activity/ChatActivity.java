package com.spadea.xqipao.ui.chart.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.EaseMsgErrorEvent;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.even.PullOrderMsgEvent;
import com.spadea.xqipao.echart.EChartHelper;
import com.spadea.xqipao.ui.chart.contacts.ChatContacts;
import com.spadea.xqipao.ui.chart.fragment.ChatFragment;
import com.spadea.xqipao.ui.chart.fragment.ChatGiftFragment;
import com.spadea.xqipao.ui.chart.fragment.EaseChatFragment;
import com.spadea.xqipao.ui.chart.presenter.ChatPresenter;
import com.spadea.xqipao.ui.chart.runtimepermissions.PermissionsManager;
import com.spadea.xqipao.ui.order.status.OrderStatusEnum;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.HOME_CHART, name = "聊天")
public class ChatActivity extends BaseActivity<ChatPresenter> implements ChatContacts.View {

    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_tight)
    TextView mTvTight;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_black)
    TextView mTvBlack;
    @BindView(R.id.riv)
    RoundedImageView mRiv;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    @Autowired
    public String nickname;
    @Autowired
    public String avatar;
    @Autowired
    public String userId;

    private LastOrderMsg mLastOrderMsg;


    public ChatActivity() {
        super(R.layout.em_activity_chat);
    }


    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        mTvTitle.setText(nickname);
        ImageUtils.loadHeadCC(avatar, mRiv);
        if (!TextUtils.isEmpty(nickname)) {
            saveEaseUser();
        }
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        chatFragment = new ChatFragment();
        chatFragment.setHbCallback(() -> {
            ARouter.getInstance().build(ARouteConstants.ME_TRANSFER).withInt("fid", chatFragment.hashCode()).withSerializable("transferUser", new TransferUserModel(nickname, user_code, avatar, userId)).navigation();
        });
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    private void saveEaseUser() {
        EaseUser easeUser = new EaseUser(userId.toLowerCase());
        easeUser.setAvatar(avatar);
        easeUser.setNickname(nickname);
        EChartHelper.getInstance().saveContact(easeUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userId.equals(MyApplication.getInstance().getUser().getEmchat_username())) {
            ToastUtils.showShort("不能私信自己");
            finish();
        } else {
            pullOrderMsg(null);
        }
        MvpPre.getEmChatUserInfo(userId);
    }

    @Override
    protected ChatPresenter bindPresenter() {
        return new ChatPresenter(this, this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @Override
    public void lastOrderMsg(LastOrderMsg msg) {
        mLastOrderMsg = msg;
        if (msg == null) {
            mLlTop.setVisibility(View.GONE);
        } else {
            mLlTop.setVisibility(View.VISIBLE);
            if (msg.getIsBoss() == 1) {
                if (msg.getType() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                    mTvMsg.setText("是否同意立即开始服务");
                    mTvLeft.setVisibility(View.VISIBLE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvLeft.setText("暂不");
                    mTvTight.setText("立即");
                } else if (OrderStatusEnum.canFinishOrder(msg.getType())) {
                    mTvMsg.setText("是否完成邀约");
                    mTvLeft.setVisibility(View.VISIBLE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvLeft.setText("退款");
                    mTvTight.setText("完成");
                } else if (OrderStatusEnum.canEvaluateOrder(msg.getType())) {
                    mTvMsg.setText("订单已完成");
                    mTvLeft.setVisibility(View.GONE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvTight.setText("评价");
                } else if (msg.getType() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                    mTvMsg.setText("主播拒绝退款");
                    mTvLeft.setVisibility(View.VISIBLE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvLeft.setText("申诉");
                    mTvTight.setText("同意");
                } else {
                    mLlTop.setVisibility(View.GONE);
                }
            } else {
                if (msg.getType() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                    mTvMsg.setText("收到一个新的订单");
                    mTvLeft.setVisibility(View.VISIBLE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvLeft.setText("拒绝");
                    mTvTight.setText("接受");
                } else if (msg.getType() == OrderStatusEnum.BE_SERVE.getValue()) {
                    mTvMsg.setText("是否立即给客户提供服务");
                    mTvLeft.setVisibility(View.GONE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvTight.setText("立即");
                } else if (OrderStatusEnum.canEvaluateOrder(msg.getType())) {
                    mTvMsg.setText("订单已完成");
                    mTvLeft.setVisibility(View.GONE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvTight.setText("评价");
                } else if (msg.getType() == OrderStatusEnum.REFUNDING.getValue()) {
                    mTvMsg.setText("客户提交退款申请");
                    mTvLeft.setVisibility(View.VISIBLE);
                    mTvTight.setVisibility(View.VISIBLE);
                    mTvLeft.setText("拒绝");
                    mTvTight.setText("同意");
                } else {
                    mLlTop.setVisibility(View.GONE);
                }
            }

        }
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

    String user_code;
    @Override
    public void userInfo(EmChatUserInfo userInfo) {
        avatar = userInfo.getHead_picture();
        nickname = userInfo.getNickname();
        user_code = userInfo.getUser_code();
        mTvTitle.setText(nickname);
        ImageUtils.loadHeadCC(avatar, mRiv);
        saveEaseUser();
        if ("1".equals(userInfo.getIs_black())) {
            mTvBlack.setVisibility(View.VISIBLE);
            if (chatFragment != null) {
                chatFragment.hideExtendMenu();
            }
        }
        if (chatFragment != null) {
            chatFragment.setUserId(userInfo.getUser_id());
        }
    }

    @Override
    public void showGiftDialog(String userId) {
        ChatGiftFragment.newInstance(userId).show(getSupportFragmentManager(), "ChatGiftFragment");
    }


    @OnClick({R.id.tv_left, R.id.tv_tight, R.id.iv_back, R.id.iv_more, R.id.tv_black, R.id.riv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                if (mLastOrderMsg.getType() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                    MvpPre.accompanyAccept(new UpdateOrderModel(mLastOrderMsg.getOrderId(), 0));
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.REFUNDING.getValue()) {
                    MvpPre.disAgreeRefund(mLastOrderMsg.getOrderId());
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                    MvpPre.bossAcceptService(new UpdateOrderModel(mLastOrderMsg.getOrderId(), 0));
                } else if (OrderStatusEnum.canFinishOrder(mLastOrderMsg.getType())) {
                    MvpPre.bossRefundOrder(mLastOrderMsg.getOrderId());
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                    MvpPre.bossAppealing(mLastOrderMsg.getOrderId(), mLastOrderMsg.getUserId(), mLastOrderMsg.getPlayUserId());
                }
                break;
            case R.id.tv_tight:
                if (mLastOrderMsg.getType() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                    MvpPre.accompanyAccept(new UpdateOrderModel(mLastOrderMsg.getOrderId(), 1));
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.BE_SERVE.getValue()) {
                    MvpPre.accompanyService(mLastOrderMsg.getOrderId());
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.REFUNDING.getValue()) {
                    MvpPre.agreeRefund(mLastOrderMsg.getOrderId());
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                    MvpPre.bossAcceptService(new UpdateOrderModel(mLastOrderMsg.getOrderId(), 1));
                } else if (OrderStatusEnum.canFinishOrder(mLastOrderMsg.getType())) {
                    MvpPre.bossConfirmOrder(mLastOrderMsg.getOrderId());
                } else if (mLastOrderMsg.getType() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                    MvpPre.bossAgreeRefuseRefund(mLastOrderMsg.getOrderId());
                } else if (OrderStatusEnum.canEvaluateOrder(mLastOrderMsg.getType())) {
                    ARouter.getInstance().build(ARouters.ORDER_SCORE).withInt("orderId", mLastOrderMsg.getOrderId()).withInt("type", mLastOrderMsg.getIsBoss()).navigation();
                }
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_more:
                ARouter.getInstance().build(ARouteConstants.CHAT_MORE).withString("emChatUserName", userId).navigation();
                break;
            case R.id.riv:
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("emchatUsername", userId).navigation();
                break;
        }
    }
}
