package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.model.styles.EaseMessageListItemStyle;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.SystemEaseChatRow;

/**
 * 官方公告
 */
public class SystemEaseChatTextPresenter extends EaseChatTextPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new SystemEaseChatRow(cxt, message, position, adapter);
    }

}
