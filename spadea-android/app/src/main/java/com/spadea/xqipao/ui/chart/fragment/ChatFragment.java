package com.spadea.xqipao.ui.chart.fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.easeui.widget.presenter.EaseChatGiftPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.echart.Constant;
import com.spadea.xqipao.ui.chart.presenter.OrderChatRowPresenter;

import java.util.List;

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {


    private static final int REQUEST_CODE_CONTEXT_MENU = 14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void setUpView() {
        setChatFragmentHelper(this);
        super.setUpView();
        // set click listener

    }

    @Override
    protected void registerExtendMenuItem() {
        //use the menu in base class

        super.registerExtendMenuItem();
    }


    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider();
    }


    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {
        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("emchatUsername", username).navigation();
    }

    @Override
    public void onAvatarLongClick(String username) {
        inputAtUsername(username);
    }


    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        //消息框点击事件，demo这里不做覆盖，如需覆盖，return true
        return false;
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        super.onCmdMessageReceived(messages);
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {
        int type = message.getType().ordinal();
        if (type == EMMessage.Type.TXT.ordinal()) {
            clipboard.setPrimaryClip(ClipData.newPlainText(null,
                    ((EMTextMessageBody) contextMenuMessage.getBody()).getMessage()));
            ToastUtils.showShort("复制成功");
        }
    }


    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {

        return false;
    }


    /**
     * chat row provider
     */
    private static final class CustomChatRowProvider implements EaseCustomChatRowProvider {
        private static final int MESSAGE_TYPE_RECV_ORDER = 1;
        private static final int MESSAGE_TYPE_RECEIVE_GIFT = 2;
        private static final int MESSAGE_TYPE_SEND_GIFT = 3;

        @Override
        public int getCustomChatRowTypeCount() {
            //here the number is the message type in EMMessage::Type
            //which is used to count the number of different chat row
            return 17;
        }

        @Override
        public int getCustomChatRowType(EMMessage message) {
            if (message.getType() == EMMessage.Type.TXT) {
                // 订单消息
                if (message.getIntAttribute(Constant.EXTRA_MSG_ACTION, 0) == 1 && message.getIntAttribute("ordderId", 0) > 0) {
                    return MESSAGE_TYPE_RECV_ORDER;
                } else if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_GIFT, false)) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECEIVE_GIFT : MESSAGE_TYPE_SEND_GIFT;
                }
            }
            return 0;
        }

        @Override
        public EaseChatRowPresenter getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
            if (message.getType() == EMMessage.Type.TXT) {
                // 订单消息
                if (message.getIntAttribute(Constant.EXTRA_MSG_ACTION, 0) == 1 && message.getIntAttribute("ordderId", 0) > 0) {
                    return new OrderChatRowPresenter();
                } else if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_GIFT, false)) {
                    return new EaseChatGiftPresenter();
                }
            }
            return null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
