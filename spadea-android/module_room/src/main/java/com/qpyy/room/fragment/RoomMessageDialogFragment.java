package com.qpyy.room.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroupReadAck;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.echart.Constant;
import com.hyphenate.easeui.echart.db.InviteMessgeDao;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.widget.RoomEaseConversationList;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.SPConstants;
import com.qpyy.libcommon.event.ConversationDelEvent;
import com.qpyy.libcommon.event.RoomChatEvent;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.NewsMessageEvent;
import com.qpyy.room.bean.NewsModel;
import com.qpyy.room.bean.NewsRefreshEvent;
import com.qpyy.room.bean.NewsTabReEvent;
import com.qpyy.room.bean.PullOrderMsgEvent;
import com.qpyy.room.contacts.NewsContacts;
import com.qpyy.room.event.RefreshConversationListEvent;
import com.qpyy.room.presenter.NewsPresenter;
import com.qpyy.room.widget.NewsItemView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/7 14:50
 * 描述 describe
 */
public class RoomMessageDialogFragment extends BaseMvpDialogFragment<NewsPresenter> implements NewsContacts.View, EMMessageListener {

    private static final String TAG = "BaseDialogFragment";
    @BindView(R2.id.tv_message_window_title)
    TextView tvMessageWindowTitle;
    @BindView(R2.id.tv_read_all_message)
    TextView tvReadAllMessage;
    @BindView(R2.id.niv_sys)
    NewsItemView nivSys;
    @BindView(R2.id.ecl_list)
    RoomEaseConversationList eclList;
    @BindView(R2.id.nsv_view)
    NestedScrollView nsvView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private List<EMConversation> mConversationList;
    private int page;
    public static final int PAGE_SIZE = 20;


    public static RoomMessageDialogFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        RoomMessageDialogFragment fragment = new RoomMessageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initView() {
        eclList.init(new ArrayList<>());
        eclList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = eclList.getItem(position);
                try {
                    JSONObject jsonObject = new JSONObject(conversation.getExtField());
                    String avatar = jsonObject.optString("avatar");
                    String nickname = jsonObject.optString("nickname");
                    DialogFragment dialogFragment = (DialogFragment) ARouter.getInstance().build(ARouteConstants.ROOM_CHART)
                            .withString("userId", conversation.conversationId())
                            .withString("nickname", nickname)
                            .withString("avatar", avatar)
                            .navigation();
                    dialogFragment.show(getFragmentManager(), "RoomChatFragment");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("删除错误数据");
                    EMClient.getInstance().chatManager().deleteConversation(conversation.conversationId(), false);
                    refresh();
                }
            }
        });
        EMClient.getInstance().chatManager().addMessageListener(this);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (mConversationList != null && mConversationList.size() > page * PAGE_SIZE) {
                    if (mConversationList.size() > (page + 1) * PAGE_SIZE) {
                        eclList.addData(mConversationList.subList(page * PAGE_SIZE, (page + 1) * PAGE_SIZE));
                    } else {
                        eclList.addData(mConversationList.subList(page * PAGE_SIZE, mConversationList.size()));
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
                MvpPre.userNews();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================RoomMessageDialogFragment");
        return R.layout.room_dialog_message_list;
    }

    @Override
    protected NewsPresenter bindPresenter() {
        return new NewsPresenter(this, getActivity());
    }


    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReSelect(NewsTabReEvent event) {
        nsvView.fling(0);
        nsvView.smoothScrollTo(0, 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRoomChat(RoomChatEvent event) {
        DialogFragment dialogFragment = (DialogFragment) ARouter.getInstance().build(ARouteConstants.ROOM_CHART)
                .withString("userId", event.getUserId())
                .withString("nickname", event.getNickName())
                .withString("avatar", event.getAvatar())
                .navigation();
        dialogFragment.show(getChildFragmentManager(), "RoomChatFragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshConversation(RefreshConversationListEvent event) {
        refresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteConversation(ConversationDelEvent event) {
        EMConversation tobeDeleteCons = eclList.getItem(event.position);
        if (tobeDeleteCons == null) {
            return;
        }
        if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), event.delete);
            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
            // To delete the native stored adked users in this conversation.
            if (event.delete) {
                EaseDingMessageHelper.get().delete(tobeDeleteCons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();
        EventBus.getDefault().post(new NewsMessageEvent());
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        EMClient.getInstance().chatManager().removeMessageListener(this);
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            MvpPre.userNews();
        }
    }


    /**
     * refresh ui
     */
    public void refresh() {
        MvpPre.refreshConversation();
        EventBus.getDefault().post(new NewsMessageEvent());
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
        MvpPre.userNews();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    //收到消息
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        for (EMMessage emMessage : list) {
            if (emMessage.getChatType().equals(EMMessage.ChatType.Chat)) {
                EaseUI.getInstance().getNotifier().vibrateAndPlayTone(emMessage);
                if (emMessage.getIntAttribute(Constant.EXTRA_MSG_ACTION, 0) == 1) {
                    int orderCount = SpUtils.getOrderNewCounts();
                    orderCount++;
                    SpUtils.setOrderNewCounts(orderCount);
                    SpUtils.setLastOrderMsg(SPConstants.ORDER_LAST_MSG);
                    EventBus.getDefault().post(new PullOrderMsgEvent());
                }
                EventBus.getDefault().post(new NewsMessageEvent());
                refresh();
            }
        }
    }

    //收到透传消息
    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    //收到已读回执
    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    //接受到群组消息体的已读回执, 消息的接收方已经阅读此消息。
    @Override
    public void onGroupMessageRead(List<EMGroupReadAck> list) {


    }

    //接受到群组消息体的已读数据更新。
    @Override
    public void onReadAckForGroupMessageUpdated() {

    }

    //收到已送达回执
    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    //消息被撤回
    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    //消息状态变动
    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        EventBus.getDefault().post(new NewsMessageEvent());
    }

    @Override
    public void userNewsSuccess(NewsModel newsModel) {
        if (newsModel.getInfo() == null) {
            nivSys.setMsg("暂无消息");
        } else {
            nivSys.setMsg(newsModel.getInfo().getContent());
        }
        nivSys.setCount(newsModel.getCount());
    }

    @Override
    public void conversationComplete(List<EMConversation> list) {
        page = 0;
        mConversationList = new ArrayList<>(list);
        if (eclList != null) {
            if (list.size() < PAGE_SIZE) {
                eclList.refresh(list);
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            } else {
                eclList.refresh(list.subList(0, PAGE_SIZE));
            }
        }
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
        }
    }


    @OnClick({R2.id.niv_sys, R2.id.tv_read_all_message})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.niv_sys) {
            nivSys.setCount(0);
            RoomSysMessageDialogFragment.show(getChildFragmentManager());
        }
        if (view.getId() == R.id.tv_read_all_message) {
            EMClient.getInstance().chatManager().markAllConversationsAsRead();
            refresh();
            MvpPre.getList();
            nivSys.setCount(0);
        }
    }

}
