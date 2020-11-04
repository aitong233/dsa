package com.hyphenate.easeui.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.pubilc.NewJoinRoomChatRow;

/**
 * 加入直播间，上麦，下麦，踢出房间，禁言，解除禁言，送礼物
 */
public class NewJoinRoomChatTextPresenter extends EaseChatTextPresenter {

    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new NewJoinRoomChatRow(cxt, message, position, adapter);
    }
}