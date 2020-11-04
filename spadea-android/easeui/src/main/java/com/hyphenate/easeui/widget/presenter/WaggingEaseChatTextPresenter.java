package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.WaggingEaseChatRow;

/**
 * 摇签
 */
public class WaggingEaseChatTextPresenter extends EaseChatTextPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new WaggingEaseChatRow(cxt, message, position, adapter);
    }
}