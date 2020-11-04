package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.NewUserEaseChatRow;
import com.hyphenate.easeui.widget.pubilc.SystemEaseChatRow;

/**
 * 新用户注册
 */
public class NewUserEaseChatTextPresenter extends EaseChatTextPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new NewUserEaseChatRow(cxt, message, position, adapter);
    }
}
