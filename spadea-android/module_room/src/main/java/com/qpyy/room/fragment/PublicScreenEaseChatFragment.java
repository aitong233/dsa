package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpFragment;

import com.qpyy.libcommon.bean.RoomUserJoinModel;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.event.PublicScreenEvent;
import com.qpyy.libcommon.event.UserInfoShowEvent;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.EaseChatAdapter;
import com.qpyy.room.bean.ClosePublicScreenEvent;
import com.qpyy.room.bean.EMMessageInfo;
import com.qpyy.room.bean.EffectEvent;
import com.qpyy.room.bean.NewsMessageEvent;
import com.qpyy.room.bean.OpenPublicScreenEvent;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomInputEvent;
import com.qpyy.room.bean.RoomUserBean;
import com.qpyy.room.bean.SendFaceEvent;
import com.qpyy.room.contacts.PublicScreenEaseChatContacts;
import com.qpyy.room.presenter.PublicScreenEaseChatPresenter;
import com.qpyy.room.widget.WelcomeAnimView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PublicScreenEaseChatFragment extends BaseMvpFragment<PublicScreenEaseChatPresenter> implements EMValueCallBack<EMChatRoom>, EMMessageListener, EMCallBack, PublicScreenEaseChatContacts.View, EMConnectionListener {

    private final static String TAG = "聊天室";

    @BindView(R2.id.tv_count)
    TextView textViewCount;
    @BindView(R2.id.wav)
    WelcomeAnimView mWelcomeAnimView;
    @BindView(R2.id.recycle_view)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_tip_close)
    TextView tv_tip_close;

    private boolean isBottom = true;
    private int count = 0;

    private String toChatUsername;
    private EMConversation conversation;

    private RoomInfoResp roomInfoResp;
    private EaseChatAdapter easeChatAdapter;


    public static PublicScreenEaseChatFragment newInstance(RoomInfoResp roomInfoResp) {
        PublicScreenEaseChatFragment transEaseChatFragment = new PublicScreenEaseChatFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EaseConstant.EXTRA_USER_ID, roomInfoResp);
        transEaseChatFragment.setArguments(bundle);
        return transEaseChatFragment;
    }

    @Override
    protected PublicScreenEaseChatPresenter bindPresenter() {
        return new PublicScreenEaseChatPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomInfoResp = (RoomInfoResp) arguments.getSerializable(EaseConstant.EXTRA_USER_ID);
        toChatUsername = roomInfoResp.getRoom_info().getChatrooms();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        EMClient.getInstance().chatManager().addMessageListener(this);
        EMClient.getInstance().addConnectionListener(this);
    }

    @Override
    public void onDestroyView() {
        EMClient.getInstance().removeConnectionListener(this);
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }

    @Override
    protected void initData() {
        onChatRoomViewCreation();
    }

    @Override
    protected void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == recyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //屏幕中最后一个可见子项的position
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //当前屏幕所看到的子项个数
                    int visibleItemCount = layoutManager.getChildCount();
                    //当前RecyclerView的所有子项个数
                    int totalItemCount = layoutManager.getItemCount();
                    //RecyclerView的滑动状态
                    if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1) {
                        if (textViewCount != null) {
                            textViewCount.setVisibility(View.GONE);
                        }
                        isBottom = true;
                        count = 0;
                    } else {
                        isBottom = false;
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(easeChatAdapter = new EaseChatAdapter());
        easeChatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EMMessageInfo item = easeChatAdapter.getItem(position);
                String userId = item.getEmMessage().getStringAttribute("user_id", "");
                if (!TextUtils.isEmpty(userId)) {
                    EventBus.getDefault().post(new UserInfoShowEvent(roomInfoResp.getRoom_info().getRoom_id(), userId));
                }
            }
        });

        //判断是否开启公屏
        setUpPublicScreen();
        //是否隐藏欢迎动画
        setEffectSwitch(SpUtils.getOpenEffect() == 1 ? new EffectEvent(true) : new EffectEvent(false));
    }

    private void setUpPublicScreen() {
        if (roomInfoResp.getRoom_info().getChat_status() == 1) {
            //如果有数据或者recycle view有item view就删除;否则程序崩溃，找不到item
            if (recyclerView.getChildCount() > 0) {
                recyclerView.removeAllViews();
                easeChatAdapter.clearData();
            }
            recyclerView.setVisibility(View.VISIBLE);//开启消息列表
            tv_tip_close.setVisibility(View.GONE);
        } else {
            tv_tip_close.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);//隐藏消息列表
        }
        count = 0;//未读数0
        isBottom = true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_trans_ease_chat;
    }


    @OnClick(R2.id.tv_count)
    public void onClick() {
        isBottom = true;
        recyclerView.scrollToPosition(easeChatAdapter.getItemCount() - 1);
        textViewCount.setVisibility(View.GONE);
    }

    /**
     * 登录聊天室
     */
    private void onChatRoomViewCreation() {
        if (EMClient.getInstance().isConnected()) {
            EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, this);
        } else {
            UserBean userBean = BaseApplication.getIns().getUser();
            EMClient.getInstance().login(userBean.getEmchat_username(), userBean.getEmchat_password(), this);
        }
    }

    @Override
    public void onSuccess(EMChatRoom emChatRoom) {
        LogUtils.e(TAG, "加入聊天室成功");
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onConversationInit();
                }
            });
        }
    }


    /**
     * 获取聊天室数据
     */
    private void onConversationInit() {
        if (conversation == null) {
            int chatType = EaseConstant.CHATTYPE_CHATROOM;
            conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
            if (!TextUtils.isEmpty(roomInfoResp.getRoom_info().getOfficial_notice())) {
                appendMessage(1, 6013, roomInfoResp.getRoom_info().getOfficial_notice());
            }
            if (!TextUtils.isEmpty(roomInfoResp.getRoom_info().getGreeting())) {
                appendMessage(1, 6014, roomInfoResp.getRoom_info().getGreeting());
            }
            appendWelcomeMessage();
        }
    }

    /**
     * 添加表情消息
     *
     * @param sendFaceEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendFaceEvent(SendFaceEvent sendFaceEvent) {
        MvpPre.sendFace(sendFaceEvent.getRoom_id(), sendFaceEvent.getFace_id(), sendFaceEvent.getPitNumber(), sendFaceEvent.getType());
        RoomUserBean userBean = roomInfoResp.getUser_info();
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(sendFaceEvent.getFace_pic(), toChatUsername);
        txtSendMessage.setAttribute("action", 6);
        txtSendMessage.setAttribute("type", 6012);
        txtSendMessage.setAttribute("face_pic", sendFaceEvent.getFace_pic());
        txtSendMessage.setAttribute("face_special", sendFaceEvent.getFace_special());
        txtSendMessage.setAttribute("user_id", userBean.getUser_id());
        txtSendMessage.setAttribute("rank_icon", userBean.getRank_icon());
        txtSendMessage.setAttribute("nobility_icon", userBean.getNobility_icon());
        txtSendMessage.setAttribute("nickname", userBean.getNickname());
        if (roomInfoResp.getRoom_info().getActual_role() == 5) {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getActual_role());
        } else {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getRole());
        }
        txtSendMessage.setAttribute("user_is_new", userBean.getUser_is_new());
        txtSendMessage.setMsgTime(System.currentTimeMillis());
        txtSendMessage.setLocalTime(System.currentTimeMillis());
        easeChatAdapter.addData(new EMMessageInfo(txtSendMessage));
        refreshSelectLast();
    }

    /**
     * 添加文本消息
     *
     * @param roomInputEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendTxtEvent(RoomInputEvent roomInputEvent) {
        RoomUserBean userBean = roomInfoResp.getUser_info();
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(roomInputEvent.text, toChatUsername);
        txtSendMessage.setAttribute("action", 2);
        txtSendMessage.setAttribute("type", 6012);
        txtSendMessage.setAttribute("user_id", userBean.getUser_id());
        txtSendMessage.setAttribute("rank_icon", userBean.getRank_icon());
        txtSendMessage.setAttribute("nobility_icon", userBean.getNobility_icon());
        txtSendMessage.setAttribute("nickname", userBean.getNickname());
        if (roomInfoResp.getRoom_info().getActual_role() == 5) {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getActual_role());
        } else {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getRole());
        }
        txtSendMessage.setAttribute("user_is_new", userBean.getUser_is_new());
        txtSendMessage.setMsgTime(System.currentTimeMillis());
        txtSendMessage.setLocalTime(System.currentTimeMillis());
        easeChatAdapter.addData(new EMMessageInfo(txtSendMessage));
        refreshSelectLast();
    }

    private void appendWelcomeMessage() {
        RoomUserBean userBean = roomInfoResp.getUser_info();
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage("加入直播间", toChatUsername);
        txtSendMessage.setAttribute("action", 3);
        txtSendMessage.setAttribute("type", 6001);
        txtSendMessage.setAttribute("user_id", userBean.getUser_id());
        txtSendMessage.setAttribute("rank_icon", userBean.getRank_icon());
        txtSendMessage.setAttribute("nobility_icon", userBean.getNobility_icon());
        txtSendMessage.setAttribute("nickname", userBean.getNickname());
        if (roomInfoResp.getRoom_info().getActual_role() == 5) {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getActual_role());
        } else {
            txtSendMessage.setAttribute("role", roomInfoResp.getRoom_info().getRole());
        }

        txtSendMessage.setAttribute("user_is_new", userBean.getUser_is_new());
        txtSendMessage.setMsgTime(System.currentTimeMillis());
        txtSendMessage.setLocalTime(System.currentTimeMillis());
        easeChatAdapter.addData(new EMMessageInfo(txtSendMessage));
    }

    private void appendMessage(int action, int type, String text) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(text, toChatUsername);
        txtSendMessage.setAttribute("action", action);
        txtSendMessage.setAttribute("type", type);
        txtSendMessage.setMsgTime(System.currentTimeMillis());
        txtSendMessage.setLocalTime(System.currentTimeMillis());
        easeChatAdapter.addData(new EMMessageInfo(txtSendMessage));
    }

    @Override
    public void onSuccess() {
        onChatRoomViewCreation();
    }

    @Override
    public void onError(int i, String s) {
        ToastUtils.show(String.format("加入聊天室失败,%s,%s", i, s));
        LogUtils.e(TAG, "加入聊天室失败：code=" + i + "  信息: " + s);
    }

    @Override
    public void onProgress(int i, String s) {
        MvpPre.logEmchat(i, s, toChatUsername);
    }


    @Override
    public void onMessageReceived(List<EMMessage> list) {
        if (conversation != null) {
            conversation.markAllMessagesAsRead();
        }

        if (recyclerView.getVisibility() == View.GONE) {
            return;
        }
        ThreadUtils.runOnUiThread(() -> {
            List<EMMessageInfo> items = new ArrayList<>();
            for (EMMessage item : list) {
                if(item.getChatType() == EMMessage.ChatType.Chat){
                    EventBus.getDefault().post(new NewsMessageEvent());
                    continue;
                }
                if (item.getChatType() != EMMessage.ChatType.ChatRoom) {
                    continue;
                }

                //如果房间id不对应，则不接收
                String roomId = item.getStringAttribute("room_id", "");
                if (!TextUtils.isEmpty(roomId) && !roomId.equals(roomInfoResp.getRoom_info().getRoom_id())) {
                    continue;
                }
                int type = item.getIntAttribute("type", 0);
                int action = item.getIntAttribute("action", 0);
                Log.e("环信type", type + "");
                Log.e("环信action", action + "");
                if (type > 6000 && type < 7000) { //屏蔽6000-7000之外的数据
                    if (type == 6001 && item.getStringAttribute("user_id", "").equals(SpUtils.getUserId())) {//屏蔽自己加入房间消息
                        continue;
                    }
                    if (type == 6010 && item.getStringAttribute("user_id", "").equals(SpUtils.getUserId())) {//屏蔽自己发送的表情消息
                        continue;
                    }
                    if (type == 6012 && item.getStringAttribute("user_id", "").equals(SpUtils.getUserId())) {//屏蔽自己发送的文本消息
                        continue;
                    }
                    items.add(new EMMessageInfo(item));
                }
            }
            easeChatAdapter.addData(items);
            if (easeChatAdapter.getItemCount() > 1000) {
                easeChatAdapter.clearSomeData();
            }
            if (isBottom) {
                count = 0;
                refreshSelectLast();
            } else if (items.size() > 0) {
                count += items.size();
                if (roomInfoResp.getRoom_info().getChat_status() == 1) {
                    if (textViewCount != null) {
                        textViewCount.setVisibility(View.VISIBLE);
                        textViewCount.setText(count + "条新消息");
                    }
                }
            }
        });
    }

    /**
     * 球球大作战
     *
     * @param messages
     */
    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }


    /**
     * 用户进入房间
     *
     * @param roomUserJoinModel
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomUserJoinModel roomUserJoinModel) {
        mWelcomeAnimView.addAnim(roomUserJoinModel);
        EventBus.getDefault().removeStickyEvent(roomUserJoinModel);
    }

    /**
     * 关闭公屏
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(ClosePublicScreenEvent closePublicScreenEvent) {
        tv_tip_close.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);//隐藏消息列表
        textViewCount.setVisibility(View.GONE);//隐藏未读消息数
        count = 0;//未读消息数清零
        isBottom = true;
        MvpPre.switchPublicScreen(roomInfoResp.getRoom_info().getRoom_id(), "0");
    }

    /**
     * 开启公屏
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(OpenPublicScreenEvent openPublicScreenEvent) {
        count = 0;//未读消息数清零
        isBottom = true;
        //如果有数据或者recycle view有item view就删除;否则程序崩溃，找不到item
        if (recyclerView.getChildCount() > 0) {
            recyclerView.removeAllViews();
            easeChatAdapter.clearData();
        }
        recyclerView.setVisibility(View.VISIBLE);//开启消息列表
        tv_tip_close.setVisibility(View.GONE);
        MvpPre.switchPublicScreen(roomInfoResp.getRoom_info().getRoom_id(), "1");
    }

    /**
     * 开关公屏 1开2关
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(PublicScreenEvent event) {
        if (roomInfoResp.getRoom_info().getRoom_id().equals(event.getRoom_id())) {
            roomInfoResp.getRoom_info().setChat_status(event.getStatus());
            setUpPublicScreen();
        }
    }

    private void refreshSelectLast() {
        if (recyclerView != null) {
            recyclerView.scrollToPosition(easeChatAdapter.getItemCount() - 1);
        }
    }

    /**
     * 特效设置
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setEffectSwitch(EffectEvent event) {
        if (event.isEffectOn()) {//特效开启
            if (!mWelcomeAnimView.animEnded) {
                mWelcomeAnimView.setVisibility(View.VISIBLE);
            }
        } else {
            mWelcomeAnimView.closeEffect();
            mWelcomeAnimView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onConnected() {
        onChatRoomViewCreation();
    }

    @Override
    public void onDisconnected(int i) {
        LogUtils.e("onDisconnected", i);
    }
}
