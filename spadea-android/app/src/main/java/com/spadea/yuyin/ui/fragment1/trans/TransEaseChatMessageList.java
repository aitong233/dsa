package com.spadea.yuyin.ui.fragment1.trans;

import android.content.Context;
import android.util.AttributeSet;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

public class TransEaseChatMessageList extends EaseChatMessageList {

    public TransEaseChatMessageList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TransEaseChatMessageList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransEaseChatMessageList(Context context) {
        super(context);
    }

    @Override
    public void init(String toChatUsername, int chatType, EaseCustomChatRowProvider customChatRowProvider) {
        this.chatType = chatType;
        this.toChatUsername = toChatUsername;
        swipeRefreshLayout.setEnabled(false);
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        messageAdapter = new TransEaseMessageAdapter(context, toChatUsername, chatType, listView);
        messageAdapter.setItemStyle(itemStyle);
        messageAdapter.setCustomChatRowProvider(customChatRowProvider);
        listView.setAdapter(messageAdapter);
        refreshSelectLast();
    }
}
