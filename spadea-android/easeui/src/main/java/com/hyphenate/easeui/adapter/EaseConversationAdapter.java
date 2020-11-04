package com.hyphenate.easeui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMConversation.EMConversationType;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseConversationList.EaseConversationListHelper;
import com.hyphenate.easeui.widget.EaseImageView;
import com.hyphenate.util.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.ConversationDelEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * conversation list adapter
 */
public class EaseConversationAdapter extends ArrayAdapter<EMConversation> {
    private static final String TAG = "ChatAllHistoryAdapter";
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;
    private ConversationFilter conversationFilter;
    private boolean notiyfyByFilter;

    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;

    public EaseConversationAdapter(Context context, int resource,
                                   List<EMConversation> objects) {
        super(context, resource, objects);
        conversationList = objects;
        copyConversationList = new ArrayList<EMConversation>();
        copyConversationList.addAll(objects);
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public EMConversation getItem(int arg0) {
        if (arg0 < conversationList.size()) {
            return conversationList.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ease_row_chat_history, parent, false);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.unreadLabel = (TextView) convertView.findViewById(R.id.unread_msg_number);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.msgState = convertView.findViewById(R.id.msg_state);
            holder.list_itease_layout = (RelativeLayout) convertView.findViewById(R.id.list_itease_layout);
            holder.motioned = (TextView) convertView.findViewById(R.id.mentioned);
            holder.line = convertView.findViewById(R.id.line);
            holder.swipeMenuLayout = convertView.findViewById(R.id.swipe_menu);
            holder.rlDelete = convertView.findViewById(R.id.rl_delete);
            holder.tvDelete = convertView.findViewById(R.id.tv_delete);
            convertView.setTag(holder);
        }
        // get conversation
        final EMConversation conversation = getItem(position);
        // get username or group id
        final String username = conversation.conversationId();
        holder.rlDelete.setVisibility(View.VISIBLE);
        holder.tvDelete.setVisibility(View.GONE);

        if (conversation.getType() == EMConversationType.GroupChat) {
            String groupId = conversation.conversationId();
            if (EaseAtMessageHelper.get().hasAtMeMsg(groupId)) {
                holder.motioned.setVisibility(View.VISIBLE);
            } else {
                holder.motioned.setVisibility(View.GONE);
            }
            // group message, show group avatar
            holder.avatar.setImageResource(R.drawable.ease_group_icon);
            EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
            holder.name.setText(group != null ? group.getGroupName() : username);
        } else if (conversation.getType() == EMConversationType.ChatRoom) {
            holder.avatar.setImageResource(R.drawable.ease_group_icon);
            EMChatRoom room = EMClient.getInstance().chatroomManager().getChatRoom(username);
            holder.name.setText(room != null && !TextUtils.isEmpty(room.getName()) ? room.getName() : username);
            holder.motioned.setVisibility(View.GONE);
        } else {
            try {
                EMMessage lastMessage = conversation.getLatestMessageFromOthers();
                JSONObject jsonObject = new JSONObject(conversation.getExtField());
                final String avatar = jsonObject.optString("avatar");
                final String nickname = jsonObject.optString("nickname");
                if (!TextUtils.isEmpty(avatar)) {
                    RequestOptions options = new RequestOptions();
                    options.centerCrop().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar);
                    Glide.with(getContext()).load(avatar).apply(options).into(holder.avatar);
                }else if (!TextUtils.isEmpty(lastMessage.getStringAttribute("avatar"))){
                    RequestOptions options = new RequestOptions();
                    options.centerCrop().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar);
                    Glide.with(getContext()).load(lastMessage.getStringAttribute("avatar")).apply(options).into(holder.avatar);
                }else {
                    Glide.with(getContext()).load(R.drawable.default_avatar).into(holder.avatar);
                }
                if (!TextUtils.isEmpty(nickname)) {
                    holder.name.setText(nickname);
                }else if (!TextUtils.isEmpty(lastMessage.getStringAttribute("nickname"))) {
                    holder.name.setText(lastMessage.getStringAttribute("nickname"));
                }else {
                    holder.name.setText(username);
                }
                holder.avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("emchatUsername", username).navigation();
                    }
                });
                holder.list_itease_layout.setOnClickListener(new View.OnClickListener() {
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
                EaseUserUtils.setUserAvatar(getContext(), username, holder.avatar);
                EaseUserUtils.setUserNick(username, holder.name);
            }
            holder.line.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            holder.motioned.setVisibility(View.GONE);
        }
        final RelativeLayout rlDelete = holder.rlDelete;
        final TextView tvDelete = holder.tvDelete;
        holder.rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlDelete.setVisibility(View.GONE);
                tvDelete.setVisibility(View.VISIBLE);
            }
        });
        final SwipeMenuLayout swipeMenuLayout = holder.swipeMenuLayout;
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenuLayout.quickClose();
                EventBus.getDefault().post(new ConversationDelEvent(position));
            }
        });
        EaseAvatarOptions avatarOptions = EaseUI.getInstance().getAvatarOptions();
        if (avatarOptions != null && holder.avatar instanceof EaseImageView) {
            EaseImageView avatarView = ((EaseImageView) holder.avatar);
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
            holder.unreadLabel.setText(String.valueOf(conversation.getUnreadMsgCount()));
            holder.unreadLabel.setVisibility(View.VISIBLE);
        } else {
            holder.unreadLabel.setVisibility(View.INVISIBLE);
        }

        if (conversation.getAllMsgCount() != 0) {
            // show the content of latest message
            EMMessage lastMessage = conversation.getLastMessage();
            String content = null;
            if (cvsListHelper != null) {
                content = cvsListHelper.onSetItemSecondaryText(lastMessage);
            }
            holder.message.setText(EaseSmileUtils.getSmiledText(getContext(), EaseCommonUtils.getMessageDigest(lastMessage, (this.getContext()))),
                    BufferType.SPANNABLE);
            if (content != null) {
                holder.message.setText(content);
            }
            holder.time.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                holder.msgState.setVisibility(View.VISIBLE);
            } else {
                holder.msgState.setVisibility(View.GONE);
            }
        }

        //set property
        holder.name.setTextColor(primaryColor);
        holder.message.setTextColor(secondaryColor);
        holder.time.setTextColor(timeColor);
        if (primarySize != 0)
            holder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, primarySize);
        if (secondarySize != 0)
            holder.message.setTextSize(TypedValue.COMPLEX_UNIT_PX, secondarySize);
        if (timeSize != 0)
            holder.time.setTextSize(TypedValue.COMPLEX_UNIT_PX, timeSize);

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyConversationList.clear();
            copyConversationList.addAll(conversationList);
            notiyfyByFilter = false;
        }
    }

    @Override
    public Filter getFilter() {
        if (conversationFilter == null) {
            conversationFilter = new ConversationFilter(conversationList);
        }
        return conversationFilter;
    }


    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setTimeColor(int timeColor) {
        this.timeColor = timeColor;
    }

    public void setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
    }

    public void setSecondarySize(int secondarySize) {
        this.secondarySize = secondarySize;
    }

    public void setTimeSize(float timeSize) {
        this.timeSize = timeSize;
    }

    public void setNewDatas(List<EMConversation> loadConversationList) {
        conversationList = loadConversationList;
        notifyDataSetChanged();
    }

    public void addData(List<EMConversation> loadConversationList) {
        conversationList.addAll(loadConversationList);
        notifyDataSetChanged();
    }


    private class ConversationFilter extends Filter {
        List<EMConversation> mOriginalValues = null;

        public ConversationFilter(List<EMConversation> mList) {
            mOriginalValues = mList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<EMConversation>();
            }
            if (prefix == null || prefix.length() == 0) {
                results.values = copyConversationList;
                results.count = copyConversationList.size();
            } else {
                if (copyConversationList.size() > mOriginalValues.size()) {
                    mOriginalValues = copyConversationList;
                }
                String prefixString = prefix.toString();
                final int count = mOriginalValues.size();
                final ArrayList<EMConversation> newValues = new ArrayList<EMConversation>();

                for (int i = 0; i < count; i++) {
                    final EMConversation value = mOriginalValues.get(i);
                    String username = value.conversationId();

                    EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
                    if (group != null) {
                        username = group.getGroupName();
                    } else {
                        EaseUser user = EaseUserUtils.getUserInfo(username);
                        // TODO: not support Nick anymore
//                        if(user != null && user.getNick() != null)
//                            username = user.getNick();
                    }

                    // First match against the whole ,non-splitted value
                    if (username.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = username.split(" ");
                        final int wordCount = words.length;

                        // Start at index 0, in case valueText starts with space(s)
                        for (String word : words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            conversationList.clear();
            if (results.values != null) {
                conversationList.addAll((List<EMConversation>) results.values);
            }
            if (results.count > 0) {
                notiyfyByFilter = true;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    private EaseConversationListHelper cvsListHelper;

    public void setCvsListHelper(EaseConversationListHelper cvsListHelper) {
        this.cvsListHelper = cvsListHelper;
    }

    private static class ViewHolder {
        /**
         * who you chat with
         */
        TextView name;
        /**
         * unread message count
         */
        TextView unreadLabel;
        /**
         * content of last message
         */
        TextView message;
        /**
         * time of last message
         */
        TextView time;
        /**
         * avatar
         */
        ImageView avatar;
        /**
         * status of last message
         */
        View msgState;
        /**
         * layout
         */
        RelativeLayout list_itease_layout;
        TextView motioned;
        View line;
        SwipeMenuLayout swipeMenuLayout;
        RelativeLayout rlDelete;
        TextView tvDelete;
    }
}

