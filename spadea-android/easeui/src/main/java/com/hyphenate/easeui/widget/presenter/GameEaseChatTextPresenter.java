package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.ExpressionEaseChatRow;
import com.hyphenate.easeui.widget.pubilc.GameEaseChatRow;

/**
 * 球球大作战
 */
public class GameEaseChatTextPresenter extends EaseChatTextPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new GameEaseChatRow(cxt, message, position, adapter);
    }
}