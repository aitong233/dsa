package com.hyphenate.easeui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseImageView;
import com.hyphenate.util.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.ConversationDelEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.hyphenate.easeui.adapter
 * 创建人 王欧
 * 创建时间 2020/8/5 3:46 PM
 * 描述 describe
 */
public class NewsConversationAdapter extends BaseQuickAdapter<EMConversation, BaseViewHolder> {
    public NewsConversationAdapter() {
        super(R.layout.ease_row_chat_history, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, EMConversation conversation) {
        // get username or group id
        final String username = conversation.conversationId();
        helper.setVisible(R.id.rl_delete, true);
        helper.setGone(R.id.tv_delete, false);
        ImageView avatarImage = helper.getView(R.id.avatar);
        TextView tvName = helper.getView(R.id.name);
        if (conversation.getType() == EMConversation.EMConversationType.GroupChat) {
            String groupId = conversation.conversationId();
            helper.setVisible(R.id.mentioned, EaseAtMessageHelper.get().hasAtMeMsg(groupId));
            // group message, show group avatar
            helper.setImageResource(R.id.avatar, R.drawable.ease_group_icon);
            EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
            helper.setText(R.id.name, group != null ? group.getGroupName() : username);
        } else if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
            helper.setImageResource(R.id.avatar, R.drawable.ease_group_icon);
            EMChatRoom room = EMClient.getInstance().chatroomManager().getChatRoom(username);
            helper.setText(R.id.name, room != null && !TextUtils.isEmpty(room.getName()) ? room.getName() : username);
            helper.setGone(R.id.mentioned, false);
        } else {
            try {
                EMMessage lastMessage = conversation.getLatestMessageFromOthers();
                JSONObject jsonObject = new JSONObject(conversation.getExtField());
                final String avatar = jsonObject.optString("avatar");
                final String nickname = jsonObject.optString("nickname");
                if (!TextUtils.isEmpty(avatar)) {
                    RequestOptions options = new RequestOptions();
                    options.centerCrop().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar);
                    Glide.with(avatarImage).load(avatar).apply(options).into(avatarImage);
                } else if (!TextUtils.isEmpty(lastMessage.getStringAttribute("avatar"))) {
                    RequestOptions options = new RequestOptions();
                    options.centerCrop().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar);
                    Glide.with(avatarImage).load(lastMessage.getStringAttribute("avatar")).apply(options).into(avatarImage);
                } else {
                    Glide.with(avatarImage).load(R.drawable.default_avatar).into(avatarImage);
                }
                if (!TextUtils.isEmpty(nickname)) {
                    helper.setText(R.id.name, nickname);
                } else if (!TextUtils.isEmpty(lastMessage.getStringAttribute("nickname"))) {
                    helper.setText(R.id.name, lastMessage.getStringAttribute("nickname"));
                } else {
                    helper.setText(R.id.name, username);
                }
                avatarImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("emchatUsername", username).navigation();
                    }
                });
                helper.getView(R.id.list_itease_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                                .withString("userId", username)
                                .withString("nickname", nickname)
                                .withString("avatar", avatar)
                                .navigation();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                EaseUserUtils.setUserAvatar(avatarImage.getContext(), username, avatarImage);
                EaseUserUtils.setUserNick(username, tvName);
            }
            helper.setGone(R.id.line, helper.getAdapterPosition() != 0);
            helper.setGone(R.id.mentioned, false);
        }
        helper.getView(R.id.rl_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setGone(R.id.rl_delete, false);
                helper.setVisible(R.id.tv_delete, true);
            }
        });
        final SwipeMenuLayout swipeMenuLayout = helper.getView(R.id.swipe_menu);
        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenuLayout.quickClose();
                EventBus.getDefault().post(new ConversationDelEvent(helper.getAdapterPosition()));
            }
        });
        EaseAvatarOptions avatarOptions = EaseUI.getInstance().getAvatarOptions();
        if (avatarOptions != null && avatarImage instanceof EaseImageView) {
            EaseImageView avatarView = ((EaseImageView) avatarImage);
            if (avatarOptions.getAvatarShape() != 0)
                avatarView.setShapeType(avatarOptions.getAvatarShape());
            if (avatarOptions.getAvatarBorderWidth() != 0)
                avatarView.setBorderWidth(avatarOptions.getAvatarBorderWidth());
            if (avatarOptions.getAvatarBorderColor() != 0)
                avatarView.setBorderColor(avatarOptions.getAvatarBorderColor());
            if (avatarOptions.getAvatarRadius() != 0)
                avatarView.setRadius(avatarOptions.getAvatarRadius());
        }
        if (conversation.getUnreadMsgCount() > 0) {
            // show unread message count
            helper.setText(R.id.unread_msg_number, String.valueOf(conversation.getUnreadMsgCount()));
            helper.setVisible(R.id.unread_msg_number, true);
        } else {
            helper.setVisible(R.id.unread_msg_number, false);
        }

        if (conversation.getAllMsgCount() != 0) {
            // show the content of latest message
            EMMessage lastMessage = conversation.getLastMessage();
            String content = null;
            TextView tvMessage = helper.getView(R.id.message);
            tvMessage.setText(EaseSmileUtils.getSmiledText(avatarImage.getContext(), EaseCommonUtils.getMessageDigest(lastMessage, mContext)),
                    TextView.BufferType.SPANNABLE);
            helper.setText(R.id.time, DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            helper.setGone(R.id.msg_state, lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL);
        }
    }
}
