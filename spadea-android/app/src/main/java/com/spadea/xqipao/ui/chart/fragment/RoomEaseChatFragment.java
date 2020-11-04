package com.spadea.xqipao.ui.chart.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.adapter.EMAChatRoomManagerListener;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseCompat;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.ui.EaseBaseFragment;
import com.hyphenate.easeui.ui.EaseChatRoomListener;
import com.hyphenate.easeui.ui.EaseGroupListener;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseAlertDialog;
import com.hyphenate.easeui.widget.EaseAlertDialog.AlertDialogUser;
import com.hyphenate.easeui.widget.EaseChatExtendMenu;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback;
import com.hyphenate.easeui.widget.RoomEaseChatInputMenu;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.PathUtil;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.utils.oss.OSSOperUtils;
import com.qpyy.module_news.event.GiftMsgEvent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.EaseMsgErrorEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * you can new an EaseChatFragment to use or you can inherit it to expand.
 * You need call setArguments to pass chatType and userId
 * <br/>
 * <br/>
 * you can see ChatActivity in demo for your reference
 */
public class RoomEaseChatFragment extends EaseBaseFragment implements EMMessageListener {
    protected static final String TAG = "EaseChatFragment";
    protected static final int REQUEST_CODE_CAMERA = 2;
    protected static final int REQUEST_CODE_LOCAL = 3;
    protected static final int REQUEST_CODE_DING_MSG = 4;
    protected static final String ACTION_TYPING_BEGIN = "TypingBegin";
    protected static final String ACTION_TYPING_END = "TypingEnd";

    protected CompositeDisposable mDisposables = new CompositeDisposable();


    /**
     * params to fragment
     */
    protected Bundle fragmentArgs;
    protected int chatType;
    protected String toChatUsername;
    protected String userId;
    protected EaseChatMessageList messageList;
    protected RoomEaseChatInputMenu inputMenu;
    protected EMConversation conversation;
    protected InputMethodManager inputManager;
    protected ClipboardManager clipboard;
    protected Handler handler = new Handler();
    protected File cameraFile;
    protected EaseVoiceRecorderView voiceRecorderView;
    protected SmartRefreshLayout swipeRefreshLayout;
    protected ListView listView;
    private View kickedForOfflineLayout;
    protected boolean isloading = true;
    protected boolean haveMoreData = true;
    protected int pagesize = 20;
    protected GroupListener groupListener;
    protected ChatRoomListener chatRoomListener;
    protected EMMessage contextMenuMessage;
    static final int ITEM_TAKE_PICTURE = 1;
    static final int ITEM_PICTURE = 2;
    static final int ITEM_LOCATION = 3;
    static final int ITEM_GIFT = 4;
    protected int[] itemStrings = {R.string.attach_picture, R.string.attach_take_pic, R.string.attach_gift};
    protected int[] itemdrawables = {R.drawable.news_ic_chat_more_picture, R.drawable.news_ic_chat_more_camera, R.drawable.news_ic_chat_gift};
    protected int[] itemIds = {ITEM_PICTURE, ITEM_TAKE_PICTURE, ITEM_GIFT};
    private boolean isMessageListInited;
    protected MyItemClickListener extendMenuItemClickListener;
    protected boolean isRoaming = true;
    private ExecutorService fetchQueue;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return inflater.inflate(R.layout.ease_room_fragment_chat, container, false);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        fragmentArgs = getArguments();
        chatType = fragmentArgs.getInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        toChatUsername = fragmentArgs.getString(EaseConstant.EXTRA_USER_ID).toLowerCase();
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * init view
     */
    protected void initView() {
        voiceRecorderView = (EaseVoiceRecorderView) getView().findViewById(R.id.voice_recorder);
        messageList = (EaseChatMessageList) getView().findViewById(R.id.message_list);
        if (chatType != EaseConstant.CHATTYPE_SINGLE)
            messageList.setShowUserNick(true);
        listView = messageList.getListView();
        kickedForOfflineLayout = getView().findViewById(R.id.layout_alert_kicked_off);
        kickedForOfflineLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onChatRoomViewCreation();
            }
        });

        extendMenuItemClickListener = new MyItemClickListener();
        inputMenu = (RoomEaseChatInputMenu) getView().findViewById(R.id.input_menu);
        registerExtendMenuItem();
        inputMenu.init(null);
        inputMenu.setChatInputMenuListener(new RoomEaseChatInputMenu.ChatInputMenuListener() {

            @Override
            public void onTyping(CharSequence s, int start, int before, int count) {
                // send action:TypingBegin cmd msg.

            }

            @Override
            public void onSendMessage(String content) {
                RetrofitClient.getInstance().sendTxtMessage(userId, "2", content, null, new BaseObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        sendTextMessage(content);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                return voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderCallback() {

                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        sendVoiceMessage(voiceFilePath, voiceTimeLength);
                    }
                });
            }

            @Override
            public void onBigExpressionClicked(EaseEmojicon emojicon) {
                sendBigExpressionMessage(emojicon.getName(), emojicon.getIdentityCode());
            }
        });

        swipeRefreshLayout = messageList.getSwipeRefreshLayout();

        inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (isRoaming) {
            fetchQueue = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * 取消所有请求
     */
    public void cancelRequest() {
        if (mDisposables != null) {
            mDisposables.clear(); // clear时网络请求会随即cancel
            mDisposables = null;
        }
    }

    protected void setUpView() {
        String nickname = getArguments().getString("nickname");
        if (TextUtils.isEmpty(nickname)) {
            EaseUser userInfo = EaseUserUtils.getUserInfo(toChatUsername);
            titleBar.setTitle(userInfo.getNickname());
        } else {
            titleBar.setTitle(nickname);
        }

        if (chatType != EaseConstant.CHATTYPE_CHATROOM) {
            onConversationInit();
            onMessageListInit();
        }
        titleBar.setLeftLayoutClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        setRefreshLayoutListener();
    }

    /**
     * register extend menu, item id need > 3 if you override this method and keep exist item
     */
    protected void registerExtendMenuItem() {
        for (int i = 0; i < itemStrings.length; i++) {
            inputMenu.registerExtendMenuItem(itemStrings[i], itemdrawables[i], itemIds[i], extendMenuItemClickListener);
        }
    }


    protected void onConversationInit() {
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        String nickname = getArguments().getString("nickname");
        String avatar = getArguments().getString("avatar");
        EaseUser easeUser = new EaseUser(toChatUsername);
        easeUser.setAvatar(avatar);
        easeUser.setNickname(nickname);
        conversation.setExtField(JSON.toJSONString(easeUser));
        conversation.markAllMessagesAsRead();
        if (!isRoaming) {
            final List<EMMessage> msgs = conversation.getAllMessages();
            int msgCount = msgs != null ? msgs.size() : 0;
            if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
                String msgId = null;
                if (msgs != null && msgs.size() > 0) {
                    msgId = msgs.get(0).getMsgId();
                }
                conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
            }
        } else {
            fetchQueue.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().chatManager().fetchHistoryMessages(
                                toChatUsername, EaseCommonUtils.getConversationType(chatType), pagesize, "");
                        final List<EMMessage> msgs = conversation.getAllMessages();
                        int msgCount = msgs != null ? msgs.size() : 0;
                        if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
                            String msgId = null;
                            if (msgs != null && msgs.size() > 0) {
                                msgId = msgs.get(0).getMsgId();
                            }
                            conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
                        }
                        messageList.refreshSelectLast();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    protected void onMessageListInit() {
        messageList.init(toChatUsername, chatType, chatFragmentHelper != null ?
                chatFragmentHelper.onSetCustomChatRowProvider() : null);
        setListItemClickListener();
        messageList.getListView().setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                inputMenu.hideExtendMenuContainer();
                return false;
            }
        });

        isMessageListInited = true;
    }

    public void hideExtendMenu() {
        hideKeyboard();
        inputMenu.hideExtendMenuContainer();
    }

    protected void setListItemClickListener() {
        messageList.setItemClickListener(new EaseChatMessageList.MessageListItemClickListener() {

            @Override
            public void onUserAvatarClick(String username) {
                if (chatFragmentHelper != null) {
                    chatFragmentHelper.onAvatarClick(username);
                }
            }

            @Override
            public boolean onResendClick(final EMMessage message) {
                EMLog.i(TAG, "onResendClick");
                new EaseAlertDialog(getContext(), R.string.resend, R.string.confirm_resend, null, new AlertDialogUser() {
                    @Override
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (!confirmed) {
                            return;
                        }
                        message.setStatus(EMMessage.Status.CREATE);
                        sendMessage(message);
                    }
                }, true).show();
                return true;
            }

            @Override
            public void onUserAvatarLongClick(String username) {
                if (chatFragmentHelper != null) {
                    chatFragmentHelper.onAvatarLongClick(username);
                }
            }

            @Override
            public void onBubbleLongClick(EMMessage message) {
                contextMenuMessage = message;
                if (chatFragmentHelper != null) {
                    chatFragmentHelper.onMessageBubbleLongClick(message);
                }
            }

            @Override
            public boolean onBubbleClick(EMMessage message) {
                if (chatFragmentHelper == null) {
                    return false;
                }
                return chatFragmentHelper.onMessageBubbleClick(message);
            }

            @Override
            public void onMessageInProgress(EMMessage message) {
                message.setMessageStatusCallback(messageStatusCallback);
            }
        });
    }

    protected void setRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (!isAdded() || getActivity() == null) {
                            return;
                        }
                        if (!isRoaming) {
                            loadMoreLocalMessage();
                        } else {
                            loadMoreRoamingMessages();
                        }
                    }
                }, 600);
            }
        });
    }

    private void loadMoreLocalMessage() {
        if (listView.getFirstVisiblePosition() == 0 && !isloading && haveMoreData) {
            List<EMMessage> messages;
            try {
                messages = conversation.loadMoreMsgFromDB(conversation.getAllMessages().size() == 0 ? "" : conversation.getAllMessages().get(0).getMsgId(),
                        pagesize);
            } catch (Exception e1) {
                swipeRefreshLayout.finishRefresh();
                return;
            }
            if (messages.size() > 0) {
                messageList.refreshSeekTo(messages.size() - 1);
                if (messages.size() != pagesize) {
                    haveMoreData = false;
                }
            } else {
                haveMoreData = false;
            }

            isloading = false;
        } else {
            ToastUtils.show(R.string.no_more_messages);
        }
        swipeRefreshLayout.finishRefresh();
    }

    private void loadMoreRoamingMessages() {
        if (!haveMoreData) {
            ToastUtils.show(R.string.no_more_messages);
            swipeRefreshLayout.finishRefresh();
            return;
        }
        if (fetchQueue != null) {
            fetchQueue.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<EMMessage> messages = conversation.getAllMessages();
                        EMClient.getInstance().chatManager().fetchHistoryMessages(
                                toChatUsername, EaseCommonUtils.getConversationType(chatType), pagesize,
                                (messages != null && messages.size() > 0) ? messages.get(0).getMsgId() : "");
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    } finally {
                        Activity activity = getActivity();
                        if (activity != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadMoreLocalMessage();
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA) { // capture new image
                if (cameraFile != null && cameraFile.exists())
                    sendImageMessage(cameraFile.getAbsolutePath());
            } else if (requestCode == REQUEST_CODE_LOCAL) { // send local image
                if (data != null) {
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        sendPicByUri(selectedImage);
                    }
                }
            } else if (requestCode == REQUEST_CODE_DING_MSG) { // To send the ding-type msg.
                String msgContent = data.getStringExtra("msg");
                EMLog.i(TAG, "To send the ding-type msg, content: " + msgContent);
                // Send the ding-type msg.
                EMMessage dingMsg = EaseDingMessageHelper.get().createDingMessage(toChatUsername, msgContent);
                sendMessage(dingMsg);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isMessageListInited)
            messageList.refresh();
        EaseUI.getInstance().pushActivity(getActivity());
        // register the event listener when enter the foreground
        EMClient.getInstance().chatManager().addMessageListener(this);

        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            EaseAtMessageHelper.get().removeAtMeGroup(toChatUsername);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EaseUI.getInstance().popActivity(getActivity());
    }

    @Override
    public void onDestroy() {
        cancelRequest();
        super.onDestroy();

        if (groupListener != null) {
            EMClient.getInstance().groupManager().removeGroupChangeListener(groupListener);
        }

        if (chatRoomListener != null) {
            EMClient.getInstance().chatroomManager().removeChatRoomListener(chatRoomListener);
        }

        if (chatType == EaseConstant.CHATTYPE_CHATROOM) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
        }
    }

    public void onBackPressed() {
        if (inputMenu.onBackPressed()) {
            getActivity().finish();
            if (chatType == EaseConstant.CHATTYPE_GROUP) {
                EaseAtMessageHelper.get().removeAtMeGroup(toChatUsername);
                EaseAtMessageHelper.get().cleanToAtUserList();
            }
            if (chatType == EaseConstant.CHATTYPE_CHATROOM) {
                EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
            }
        }
    }

    protected void onChatRoomViewCreation() {
        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Joining......");
        EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, new EMValueCallBack<EMChatRoom>() {

            @Override
            public void onSuccess(final EMChatRoom value) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity().isFinishing() || !toChatUsername.equals(value.getId()))
                            return;
                        pd.dismiss();
                        onConversationInit();
                        onMessageListInit();
                        // Dismiss the click-to-rejoin layout.
                        kickedForOfflineLayout.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onError(final int error, String errorMsg) {
                // TODO Auto-generated method stub
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                });
                getActivity().finish();
            }
        });
    }

    // implement methods in EMMessageListener
    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages) {
            String username = null;
            // group message
            if (message.getChatType() == ChatType.GroupChat || message.getChatType() == ChatType.ChatRoom) {
                username = message.getTo();
            } else {
                // single chat message
                username = message.getFrom();
            }

            // if the message is for current conversation
            if (username.equals(toChatUsername) || message.getTo().equals(toChatUsername) || message.conversationId().equals(toChatUsername)) {
                messageList.refreshSelectLast();
                conversation.markMessageAsRead(message.getMsgId());
            }
            EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        for (final EMMessage msg : messages) {
            final EMCmdMessageBody body = (EMCmdMessageBody) msg.getBody();
            EMLog.i(TAG, "Receive cmd message: " + body.action() + " - " + body.isDeliverOnlineOnly());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (ACTION_TYPING_BEGIN.equals(body.action()) && msg.getFrom().equals(toChatUsername)) {
                        titleBar.setTitle(getString(R.string.alert_during_typing));
                    } else if (ACTION_TYPING_END.equals(body.action()) && msg.getFrom().equals(toChatUsername)) {
                        titleBar.setTitle(toChatUsername);
                    }
                }
            });
        }
    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        if (isMessageListInited) {
            messageList.refresh();
        }
    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {
        if (isMessageListInited) {
            messageList.refresh();
        }
    }

    @Override
    public void onMessageRecalled(List<EMMessage> messages) {
        if (isMessageListInited) {
            messageList.refresh();
        }
    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object change) {
        if (isMessageListInited) {
            messageList.refresh();
        }
    }

    /**
     * handle the click event for extend menu
     */
    class MyItemClickListener implements EaseChatExtendMenu.EaseChatExtendMenuItemClickListener {

        @Override
        public void onClick(int itemId, View view) {
            if (chatFragmentHelper != null) {
                if (chatFragmentHelper.onExtendMenuItemClick(itemId, view)) {
                    return;
                }
            }
            switch (itemId) {
                case ITEM_TAKE_PICTURE:
                    selectPicFromCamera();
                    break;
                case ITEM_PICTURE:
                    selectPicFromLocal();
                    break;
                case ITEM_LOCATION:

                    break;
                case ITEM_GIFT:
                    ChatGiftFragment.newInstance(userId).show(getChildFragmentManager(), "ChatGiftFragment");
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * input @
     *
     * @param username
     */
    protected void inputAtUsername(String username, boolean autoAddAtSymbol) {
        if (EMClient.getInstance().getCurrentUser().equals(username) ||
                chatType != EaseConstant.CHATTYPE_GROUP) {
            return;
        }
        EaseAtMessageHelper.get().addAtUser(username);
        EaseUser user = EaseUserUtils.getUserInfo(username);
        if (user != null) {
            username = user.getNickname();
        }
        if (autoAddAtSymbol)
            inputMenu.insertText("@" + username + " ");
        else
            inputMenu.insertText(username + " ");
    }


    /**
     * input @
     *
     * @param username
     */
    protected void inputAtUsername(String username) {
        inputAtUsername(username, true);
    }


    //send message
    protected void sendTextMessage(String content) {
        if (EaseAtMessageHelper.get().containsAtUsername(content)) {
            sendAtMessage(content);
        } else {
            EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
            sendMessage(message);
        }
    }

    /**
     * send @ message, only support group chat message
     *
     * @param content
     */
    @SuppressWarnings("ConstantConditions")
    private void sendAtMessage(String content) {
        if (chatType != EaseConstant.CHATTYPE_GROUP) {
            EMLog.e(TAG, "only support group chat message");
            return;
        }
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
        if (EMClient.getInstance().getCurrentUser().equals(group.getOwner()) && EaseAtMessageHelper.get().containsAtAll(content)) {
            message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL);
        } else {
            message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG,
                    EaseAtMessageHelper.get().atListToJsonArray(EaseAtMessageHelper.get().getAtMessageUsernames(content)));
        }
        sendMessage(message);

    }


    protected void sendBigExpressionMessage(String name, String identityCode) {
        EMMessage message = EaseCommonUtils.createExpressionMessage(toChatUsername, name, identityCode);
        sendMessage(message);
    }

    protected void sendVoiceMessage(String filePath, int length) {
        uploadVoice(filePath, length);
    }

    private void uploadVoice(String filePath, int length) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        String url = OSSOperUtils.getPath(file, 1);
        OSSOperUtils.newInstance().putObjectMethod(url, file.getPath(), new OSSOperUtils.OssCallback() {
            @Override
            public void onSuccess() {
                checkVoice(OSSOperUtils.AliYunOSSURLFile + url, filePath, length);
            }

            @Override
            public void onFail() {
                ToastUtils.show("发送失败");
            }
        });
    }

    private void checkVoice(String voiceUrl, String filePath, int length) {
        RetrofitClient.getInstance().sendAudioMessage(userId, voiceUrl, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(String s) {
                EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
                sendMessage(message);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtils.show("发送失败");
            }
        });
    }

    protected void sendImageMessage(String imagePath) {
        uploadImage(imagePath);
    }

    protected void sendCheckedImageMesage(String imagePath) {
        EMMessage message = EMMessage.createImageSendMessage(imagePath, false, toChatUsername);
        sendMessage(message);
    }

    private void uploadImage(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) {
            return;
        }
        String url = OSSOperUtils.getPath(file, 0);
        OSSOperUtils.newInstance().putObjectMethod(url, file.getPath(), new OSSOperUtils.OssCallback() {
            @Override
            public void onSuccess() {
                checkImage(OSSOperUtils.AliYunOSSURLFile + url, imagePath);
            }

            @Override
            public void onFail() {
                ToastUtils.show("发送失败");
            }
        });
    }

    private void checkImage(String imageUrl, String imagePath) {
        RetrofitClient.getInstance().sendImageMessage(userId, imageUrl, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(String s) {
                sendCheckedImageMesage(imagePath);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtils.show("发送失败");
            }
        });
    }

    protected void sendLocationMessage(double latitude, double longitude, String locationAddress) {
        EMMessage message = EMMessage.createLocationSendMessage(latitude, longitude, locationAddress, toChatUsername);
        sendMessage(message);
    }

    protected void sendVideoMessage(String videoPath, String thumbPath, int videoLength) {
        EMMessage message = EMMessage.createVideoSendMessage(videoPath, thumbPath, videoLength, toChatUsername);
        sendMessage(message);
    }

    protected void sendFileMessage(String filePath) {
        EMMessage message = EMMessage.createFileSendMessage(filePath, toChatUsername);
        sendMessage(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendGiftMessage(GiftMsgEvent event) {
        GiftModel giftModel = event.giftModel;
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage("送给" + giftModel.getName(), toChatUsername);
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_ACTION, 2);
        txtSendMessage.setAttribute(EaseConstant.MESSAGE_ATTR_IS_GIFT, true);
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_ID, giftModel.getId());
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_PIC, giftModel.getPicture());
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_NAME, giftModel.getName());
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_PRICE, giftModel.getPrice());
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_SPECIAL, giftModel.getSpecial());
        txtSendMessage.setAttribute(EaseConstant.EXTRA_MSG_GIFT_NUM, event.num);
        sendMessage(txtSendMessage);
    }


    protected void sendMessage(EMMessage message) {
        if (message == null) {
            return;
        }
        UserBean user = MyApplication.getInstance().getUser();
        int chat_min_level = MyApplication.getInstance().chat_min_level;
        if (!message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_GIFT, false) && user.getRank_info() != null && user.getRank_info().rank_id < chat_min_level) {
            ToastUtils.show(String.format("您的用户等级不足%s级，请先提升个人等级。", chat_min_level));
            return;
        }
        if (chatFragmentHelper != null) {
            //set extension
            chatFragmentHelper.onSetMessageAttributes(message);
        }
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            message.setChatType(ChatType.GroupChat);
        } else if (chatType == EaseConstant.CHATTYPE_CHATROOM) {
            message.setChatType(ChatType.ChatRoom);
        }
        message.setMessageStatusCallback(messageStatusCallback);
        message.setAttribute("nickname", user.getNickname());
        message.setAttribute("avatar", user.getHead_picture());
        message.setAttribute("user_id", user.getUser_id());
        message.setAttribute("rank_id", user.getRank_id());
        message.setAttribute("sex", String.valueOf(user.getSex()));
        EMClient.getInstance().chatManager().sendMessage(message);
        //refresh ui
        if (isMessageListInited) {
            messageList.refreshSelectLast();
        }
    }


    //===================================================================================

    protected EMCallBack messageStatusCallback = new EMCallBack() {
        @Override
        public void onSuccess() {
            if (isMessageListInited) {
                messageList.refresh();
            }
        }

        @Override
        public void onError(int code, String error) {
            Log.i("EaseChatRowPresenter", "onError: " + code + ", error: " + error);
            if (isMessageListInited) {
                messageList.refresh();
                EventBus.getDefault().post(new EaseMsgErrorEvent());
            }
        }

        @Override
        public void onProgress(int progress, String status) {
            Log.i(TAG, "onProgress: " + progress);
            if (isMessageListInited) {
                messageList.refresh();
            }
        }
    };

    /**
     * send image
     *
     * @param selectedImage
     */
    protected void sendPicByUri(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;

            if (picturePath == null || picturePath.equals("null")) {
                Toast toast = Toast.makeText(getActivity(), R.string.cant_find_pictures, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            sendImageMessage(picturePath);
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {
                Toast toast = Toast.makeText(getActivity(), R.string.cant_find_pictures, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;

            }
            sendImageMessage(file.getAbsolutePath());
        }

    }

    /**
     * send file
     *
     * @param uri
     */
    protected void sendFileByUri(Uri uri) {
        String filePath = EaseCompat.getPath(getActivity(), uri);
        EMLog.i(TAG, "sendFileByUri: " + filePath);
        if (filePath == null) {
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            Toast.makeText(getActivity(), R.string.File_does_not_exist, Toast.LENGTH_SHORT).show();
            return;
        }
        sendFileMessage(filePath);
    }

    /**
     * capture new image
     */
    protected void selectPicFromCamera() {
        if (!EaseCommonUtils.isSdcardExist()) {
            Toast.makeText(getActivity(), R.string.sd_card_does_not_exist, Toast.LENGTH_SHORT).show();
            return;
        }

        cameraFile = new File(PathUtil.getInstance().getImagePath(), EMClient.getInstance().getCurrentUser()
                + System.currentTimeMillis() + ".jpg");
        //noinspection ResultOfMethodCallIgnored
        cameraFile.getParentFile().mkdirs();
        startActivityForResult(
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, EaseCompat.getUriForFile(getContext(), cameraFile)),
                REQUEST_CODE_CAMERA);
    }

    /**
     * select local image
     */
    protected void selectPicFromLocal() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_LOCAL);
    }


    /**
     * hide
     */
    protected void hideKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * listen the group event
     */
    class GroupListener extends EaseGroupListener {

        @Override
        public void onUserRemoved(final String groupId, String groupName) {
            getActivity().runOnUiThread(new Runnable() {

                public void run() {
                    if (toChatUsername.equals(groupId)) {
                        Toast.makeText(getActivity(), R.string.you_are_group, Toast.LENGTH_LONG).show();
                        Activity activity = getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                }
            });
        }

        @Override
        public void onGroupDestroyed(final String groupId, String groupName) {
            // prompt group is dismissed and finish this activity
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (toChatUsername.equals(groupId)) {
                        Toast.makeText(getActivity(), R.string.the_current_group_destroyed, Toast.LENGTH_LONG).show();
                        Activity activity = getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                }
            });
        }

        @Override
        public void onWhiteListAdded(String s, List<String> list) {

        }

        @Override
        public void onWhiteListRemoved(String s, List<String> list) {

        }

        @Override
        public void onAllMemberMuteStateChanged(String s, boolean b) {

        }
    }

    /**
     * listen chat room event
     */
    class ChatRoomListener extends EaseChatRoomListener {

        @Override
        public void onChatRoomDestroyed(final String roomId, final String roomName) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (roomId.equals(toChatUsername)) {
                        Toast.makeText(getActivity(), R.string.the_current_chat_room_destroyed, Toast.LENGTH_LONG).show();
                        Activity activity = getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                }
            });
        }

        @Override
        public void onRemovedFromChatRoom(final int reason, final String roomId, final String roomName, final String participant) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (roomId.equals(toChatUsername)) {
                        if (reason == EMAChatRoomManagerListener.BE_KICKED) {
                            Toast.makeText(getActivity(), R.string.quiting_the_chat_room, Toast.LENGTH_LONG).show();
                            Activity activity = getActivity();
                            if (activity != null && !activity.isFinishing()) {
                                activity.finish();
                            }
                        } else { // BE_KICKED_FOR_OFFLINE
                            // Current logged in user be kicked out by server for current user offline,
                            // show disconnect title bar, click to rejoin.
                            Toast.makeText(getActivity(), "User be kicked for offline", Toast.LENGTH_SHORT).show();
                            kickedForOfflineLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        @Override
        public void onWhiteListAdded(String s, List<String> list) {

        }

        @Override
        public void onWhiteListRemoved(String s, List<String> list) {

        }

        @Override
        public void onAllMemberMuteStateChanged(String s, boolean b) {

        }


        @Override
        public void onMemberJoined(final String roomId, final String participant) {
            if (roomId.equals(toChatUsername)) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "member join:" + participant, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        @Override
        public void onMemberExited(final String roomId, final String roomName, final String participant) {
            if (roomId.equals(toChatUsername)) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "member exit:" + participant, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }


    }

    protected EaseChatFragment.EaseChatFragmentHelper chatFragmentHelper;

    public void setChatFragmentHelper(EaseChatFragment.EaseChatFragmentHelper chatFragmentHelper) {
        this.chatFragmentHelper = chatFragmentHelper;
    }

}
