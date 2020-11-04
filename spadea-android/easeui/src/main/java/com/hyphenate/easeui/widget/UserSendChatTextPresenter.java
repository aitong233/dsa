package com.hyphenate.easeui.widget;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.presenter.EaseChatTextPresenter;
import com.hyphenate.easeui.widget.pubilc.UserSendRoomChatRow;

/**
 * 用户发送消息
 */
public class UserSendChatTextPresenter extends EaseChatTextPresenter {

    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new UserSendRoomChatRow(cxt, message, position, adapter);
    }
}