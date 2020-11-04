package com.hyphenate.easeui.widget.chatrow;

import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.widget.UserSendChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.hyphenate.easeui.widget.presenter.ExpressionEaseChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.GameEaseChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.NewJoinRoomChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.NewUserEaseChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.SystemEaseChatTextPresenter;
import com.hyphenate.easeui.widget.presenter.WaggingEaseChatTextPresenter;
import com.qpyy.libcommon.utils.LogUtils;

public class PubilcEaseCustomChatRowProvider implements EaseCustomChatRowProvider {


    @Override
    public int getCustomChatRowTypeCount() {
        return 8;
    }

    @Override
    public int getCustomChatRowType(EMMessage message) {
        if (message.getType() == EMMessage.Type.TXT) {
            int action = message.getIntAttribute(EaseConstant.PUBLIC_SCREEN_MESSAGE_TYPE, 0);
            switch (action) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return action;
            }
        }
        return 0;
    }

    @Override
    public EaseChatRowPresenter getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
        if (message.getType() == EMMessage.Type.TXT) {
            int action = message.getIntAttribute(EaseConstant.PUBLIC_SCREEN_MESSAGE_TYPE, 0);
            LogUtils.e("消息类型", action);
            /**
             * 1.官方公告
             * 2.用户发送消息
             * 3.加入直播间，上麦，下麦，踢出房间，禁言，解除禁言，送礼物
             * 4.新用户注册
             * 5.摇签
             * 6.表情
             * 7.球球大作战
             */
            switch (action) {
                case 1:
                    return new SystemEaseChatTextPresenter();
                case 2:
                    return new UserSendChatTextPresenter();
                case 3:
                    return new NewJoinRoomChatTextPresenter();
                case 7:
                    return new NewUserEaseChatTextPresenter();
                case 5:
                    return new WaggingEaseChatTextPresenter();
                case 6:
                    return new ExpressionEaseChatTextPresenter();
                case 8:
                    return new GameEaseChatTextPresenter();
            }
        }
        return null;
    }

}
