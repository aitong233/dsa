package com.spadea.yuyin.ui.fragment1.trans;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.presenter.EaseChatTextPresenter;

/**
 * Created by zhangsong on 17-10-12.
 */

public class TransEaseChatTextPresenter extends EaseChatTextPresenter {

    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new TransEaseChatRowText(cxt, message, position, adapter);
    }
}
