package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.ExpressionEaseChatRow;


/**
 * 表情
 */
public class ExpressionEaseChatTextPresenter extends EaseChatTextPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new ExpressionEaseChatRow(cxt, message, position, adapter);
    }
}