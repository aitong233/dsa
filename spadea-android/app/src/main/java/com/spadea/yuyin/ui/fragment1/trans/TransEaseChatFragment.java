package com.spadea.yuyin.ui.fragment1.trans;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.GiftBean;
import com.spadea.xqipao.data.even.EaseInitEvent;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.utils.view.room.approach.WelcomeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 */
public class TransEaseChatFragment extends BaseFragment implements AbsListView.OnScrollListener {
    protected static final String TAG = "EaseChatFragment";

    protected int chatType = EaseConstant.CHATTYPE_CHATROOM;
    protected ListView listView;
    protected String toChatUsername;

    @BindView(R.id.message_list)
    TransEaseChatMessageList messageList;
    @BindView(R.id.iv_un_mesg)
    ImageView ivUnMesg;
    @BindView(R.id.welcome_view)
    WelcomeView mWelcomeView;

    private boolean isMessageListInited;
    private boolean isBottom = true;
    protected EMConversation conversation;
    private int role;


    public static TransEaseChatFragment newInstance(String toChatUsername, int role) {
        TransEaseChatFragment transEaseChatFragment = new TransEaseChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, toChatUsername);
        bundle.putInt("role", role);
        transEaseChatFragment.setArguments(bundle);
        return transEaseChatFragment;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onChatRoomViewCreation();
        }
    };


    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        toChatUsername = getArguments().getString(EaseConstant.EXTRA_USER_ID);
        role = getArguments().getInt("role");
        onChatRoomViewCreation();
    }

    @Override
    protected void initView(View rootView) {
        listView = messageList.getListView();
    }

    @Override
    protected void initListener() {
        listView.setOnScrollListener(this);
    }


    @OnClick(R.id.iv_un_mesg)
    public void onclick(View view) {
        messageList.refreshSelectLast();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.trans_ease_fragment_chat;
    }

    protected void onChatRoomViewCreation() {
        EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(final EMChatRoom value) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity().isFinishing() || !toChatUsername.equals(value.getId()))
                            return;
                        onConversationInit();
                        EventBus.getDefault().post(new EaseInitEvent());
                    }
                });
            }

            @Override
            public void onError(final int error, String errorMsg) {
                boolean connected = EMClient.getInstance().isConnected();
                if (!connected) {
                    UserBean user = MyApplication.getInstance().getUser();
                    EMClient.getInstance().login(user.getEmchat_username(), user.getEmchat_password(), new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            handler.sendEmptyMessageDelayed(0, 3000);
                        }

                        @Override
                        public void onError(int i, String s) {

                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });
                }
            }
        });
    }

    protected void onConversationInit() {
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        conversation.clearAllMessages();
        messageList.init(toChatUsername, chatType, null);
        isMessageListInited = true;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isMessageListInited) {
            messageList.refresh();
        }
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        EMClient.getInstance().chatManager().removeMessageListener(emMessageListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }

    public void onBackPressed() {
        getActivity().finish();
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }


    private void receiveCmdMessage(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        if (data != null) {
            String type = jsonObject.getString("type");
            String message = jsonObject.getString("message");
            if (!TextUtils.isEmpty(type)) {

            }
        }
    }

    public void sendOverGameCmdMessage(String pitNum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "overGame");
        jsonObject.put("message", pitNum);
        sendCmdMsg(jsonObject.toString());
    }


    public void sendStartGameCmdMessage(String pitNum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "startGame");
        jsonObject.put("message", pitNum);
        sendCmdMsg(jsonObject.toString());
    }

    public void sendOpenGameCmdMessage(String pitNum, String qiu1, String qiu2, String qiu3) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "openGame");
        JSONObject data = new JSONObject();
        data.put("qiu1", qiu1);
        data.put("qiu2", qiu2);
        data.put("qiu3", qiu3);
        data.put("pitNum", pitNum);
        jsonObject.put("message", data);
        sendCmdMsg(jsonObject.toString());
    }

    public void sendCountDownTimeMessage(String pitNum, String time) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "CountTime");
        JSONObject data = new JSONObject();
        data.put("pitNumber", pitNum);
        data.put("time", time);
        jsonObject.put("message", data);
        sendCmdMsg(jsonObject.toString());
    }

    /**
     * 发送透传消息
     */
    public void sendCmdMsg(String action) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        cmdMsg.setChatType(ChatType.ChatRoom);
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        cmdMsg.setTo(toChatUsername);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }


    /**
     * 聊天室发送文字消息
     *
     * @param content
     * @param role
     */
    public void sendText(String content, int role) {
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        message.setAttribute("role", role);
        sendMessage(message);
    }

    /**
     * 发送球消息
     *
     * @param qiu1
     * @param qiu2
     * @param qiu3
     */
    public void sendOpenGameQiuMessage(String pitNum, String content, String qiu1, String qiu2, String qiu3) {
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        message.setAttribute("action", 11);
        message.setAttribute("qiu1", qiu1);
        message.setAttribute("qiu2", qiu2);
        message.setAttribute("qiu3", qiu3);
        sendMessage(message);
        sendOpenGameCmdMessage(pitNum, qiu1, qiu2, qiu3);
    }

    /**
     * 发送欢迎消息  <欢迎xxx进入房间>
     *
     * @param userName
     */
    public void sendWelcomeMessage(String userName) {
        UserBean userBean = MyApplication.getInstance().getUser();
        //等级大于0时显示等级
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage("进入房间", toChatUsername);
        txtSendMessage.setAttribute("action", 666);
        txtSendMessage.setAttribute("nickname", userName);
        txtSendMessage.setAttribute("rank_id", userBean.getRank_id());
        sendMessage(txtSendMessage);
//        if (userBean.getRank_info().getRank_id() > 0) {
//
//        } else {
//            String text = "<font color='#FFFFFF'>欢迎</font> <font color='#E2BC41'>" + userName + "</font> <font color='#FFFFFF'>进入房间</font>";
//            EMMessage txtSendMessage = EMMessage.createTxtSendMessage(text, toChatUsername);
//            txtSendMessage.setAttribute("action", 1);
//            sendMessage(txtSendMessage);
//        }
//        mWelcomeView.loadGift(new WelcomeView.WelcomeBean(MyApplication.getInstance().getUser().getNickname(), MyApplication.getInstance().getUser().getRank_info().getRank_id(), MyApplication.getInstance().getUser().getRank_info().getRank_name()));

    }


    /**
     * 发送房间欢迎语消息
     *
     * @param greeting
     */
    public void sendGreetingMessage(String greeting) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(greeting, toChatUsername);
        txtSendMessage.setAttribute("action", 5);
        txtSendMessage.setAttribute("type", 1);
        addMessage(txtSendMessage);
    }

    /**
     * 发送官方公告
     *
     * @param officialNotice
     */
    public void sendOfficialNoticeMessage(String officialNotice) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(officialNotice, toChatUsername);
        txtSendMessage.setAttribute("action", 8);
        txtSendMessage.setAttribute("type", 1);
        txtSendMessage.setMsgTime(System.currentTimeMillis());
        txtSendMessage.setLocalTime(System.currentTimeMillis());
        addMessage(txtSendMessage);
    }


    /**
     * 被取消禁言
     * 被禁言
     * 被禁麦
     * 被下麦
     *
     * @param text
     */
    public void sendOperationMessage(String text) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(text, toChatUsername);
        txtSendMessage.setAttribute("action", 6);
        sendMessage(txtSendMessage);
    }

    /**
     * 用户自己操作消息
     *
     * @param text
     */
    public void sendActionMessage(String text) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage(text, toChatUsername);
        txtSendMessage.setAttribute("action", 4);
        sendMessage(txtSendMessage);
    }


    /**
     * 发送表情消息
     *
     * @param faceId       表情ID
     * @param facePic      表情图标
     * @param faceName     表情名称
     * @param faceSpectial
     * @param randomMum    抽签随机数
     * @param isDraw       true 抽签   false 表情
     */
    public void sendEmojiMessage(String faceId, String facePic, String faceName, String faceSpectial, String randomMum, boolean isDraw, int role) {
        EMMessage emojiMessage = EMMessage.createTxtSendMessage("发送表情" + faceName, toChatUsername);
        emojiMessage.setChatType(ChatType.ChatRoom);
        emojiMessage.setAttribute("action", 3);
        emojiMessage.setAttribute("face_id", faceId);
        emojiMessage.setAttribute("face_pic", facePic);
        emojiMessage.setAttribute("face_name", faceName);
        emojiMessage.setAttribute("face_spectial", faceSpectial);
        emojiMessage.setAttribute("role", role);
        if (isDraw) {
            emojiMessage.setAttribute("random_num", randomMum);
        }
        sendMessage(emojiMessage);
    }

    /**
     * 发送抽签消息
     *
     * @param randomNum 抽签随机数
     */
    public void sendDrawMessage(String randomNum, int role) {
        EMMessage drawMessage = EMMessage.createTxtSendMessage("发送表情抽签", toChatUsername);
        drawMessage.setAttribute("action", 3);
        drawMessage.setAttribute("random_num", randomNum);
        drawMessage.setAttribute("role", role);
        sendMessage(drawMessage);
    }

    /**
     * 发送礼物消息
     *
     * @param giftId
     * @param giftPic
     * @param giftName
     * @param giftPrice
     * @param giftSpectial
     * @param giftMum
     * @param pits
     */
    public void sendGiftMessage(String userName, String giftId, String giftPic, String giftName, String giftPrice, String giftSpectial, String giftMum, String pits) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage("送给" + userName, toChatUsername);
        txtSendMessage.setAttribute("action", 2);
        txtSendMessage.setAttribute("gift_id", giftId);
        txtSendMessage.setAttribute("gift_pic", giftPic);
        txtSendMessage.setAttribute("gift_name", giftName);
        txtSendMessage.setAttribute("gift_price", giftPrice);
        txtSendMessage.setAttribute("gift_spectial", giftSpectial);
        txtSendMessage.setAttribute("gift_num", giftMum);
        txtSendMessage.setAttribute("pits", pits);
        sendMessage(txtSendMessage);
    }


    public void sendMessage(EMMessage message) {
        UserBean user = MyApplication.getInstance().getUser();
        RankInfo rankInfo = user.getRank_info();
        message.setChatType(ChatType.ChatRoom);
        message.setMessageStatusCallback(messageStatusCallback);
        message.setAttribute("user_id", user.getUser_id());
        message.setAttribute("nickname", user.getNickname());
        message.setAttribute("avatar", user.getHead_picture());
        message.setAttribute("rank_id", rankInfo.getRank_id());
        message.setAttribute("rank_name", rankInfo.getRank_name());
        message.setAttribute("nobility_id", rankInfo.getNobility_id());
        message.setAttribute("nobility_name", rankInfo.getNobility_name());
        message.setAttribute("user_is_new", user.getUser_is_new());
        if (user.getRole() == 5) {
            role = 5;
        }
        message.setAttribute("role", role);
        EMClient.getInstance().chatManager().sendMessage(message);
        messageList.refreshSelectLast();
    }

    private void addMessage(EMMessage message) {
        message.setMessageStatusCallback(messageStatusCallback);
        conversation.appendMessage(message);
    }


    public void leaveChatRoom() {
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }

    public void clearAllMessages() {
        conversation.clearAllMessages();
        messageList.refresh();
    }


    protected EMCallBack messageStatusCallback = new EMCallBack() {
        @Override
        public void onSuccess() {
            if (isMessageListInited) {
                messageList.refresh();
            }
        }

        @Override
        public void onError(int code, String error) {
            if (isMessageListInited) {
                messageList.refresh();
            }
        }

        @Override
        public void onProgress(int progress, String status) {
            if (isMessageListInited) {
                messageList.refresh();
            }
        }
    };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                // 判断滚动到底部
                if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
                    ivUnMesg.setVisibility(View.GONE);
                    isBottom = true;
                } else {
                    isBottom = false;
                }
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                isBottom = false;
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    private EChartRoomessageListener emMessageListener = new EChartRoomessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                if (message.getChatType()!=ChatType.Chat){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isBottom) {
                                if (messageList != null) {
                                    messageList.refreshSelectLast();
                                }
                            } else {
                                ivUnMesg.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                conversation.markMessageAsRead(message.getMsgId());
                int allMsgCount = conversation.getAllMsgCount();
                if (allMsgCount >= 800) {
                    List<EMMessage> allMessages = conversation.getAllMessages();
                    conversation.removeMessage(allMessages.get(0).getMsgId());
                }
                messageList.refresh();
                switch (message.getIntAttribute("action", 0)) {
                    case 20200726:
                        GiftBean giftBean = new GiftBean();
                        giftBean.setRoom_id(message.getStringAttribute("room_id",""));
                        giftBean.setId(message.getStringAttribute("gift_id", ""));
                        giftBean.setPicture(message.getStringAttribute("gift_pic", ""));
                        giftBean.setName(message.getStringAttribute("gift_name", ""));
                        giftBean.setPrice(message.getStringAttribute("gift_price", ""));
                        giftBean.setSpecial(message.getStringAttribute("gift_spectial", ""));
                        giftBean.setPits(message.getStringAttribute("pits", ""));
                        EventBus.getDefault().post(giftBean);
                        break;
                    case 666:
                        break;
                }
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                EMCmdMessageBody emCmdMessageBody = (EMCmdMessageBody) message.getBody();
                String action = emCmdMessageBody.action();
                if (!TextUtils.isEmpty(action)) {
                    receiveCmdMessage(action);
                }
            }
        }
    };

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


}
