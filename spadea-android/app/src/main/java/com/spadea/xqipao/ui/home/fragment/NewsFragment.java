package com.spadea.xqipao.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NotificationUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroupReadAck;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.ConversationDelEvent;
import com.qpyy.module_news.R2;
import com.qpyy.module_news.utils.NotificationPageHelper;
import com.qpyy.module_news.widget.NewsItemView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.data.NewsTabReEvent;
import com.spadea.xqipao.data.even.NewsMessageEvent;
import com.spadea.xqipao.data.even.PullOrderMsgEvent;
import com.spadea.xqipao.echart.Constant;
import com.spadea.xqipao.echart.db.InviteMessgeDao;
import com.spadea.xqipao.ui.home.presenter.NewsPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.contacts.NewsContacts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsFragment extends BaseFragment<NewsPresenter> implements EMMessageListener, NewsContacts.View {
    @BindView(R.id.scroll_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.list)
    EaseConversationList conversationListView;
    @BindView(R2.id.niv_sys)
    NewsItemView mNivSys;
    @BindView(R2.id.cl_notification)
    ConstraintLayout mClNotification;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private List<EMConversation> mConversationList;
    private int page;
    public static final int PAGE_SIZE = 20;

    @Override
    protected NewsPresenter bindPresenter() {
        return new NewsPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        refresh();
    }

    @Override
    protected void initView(View rootView) {
//        registerForContextMenu(conversationListView);
        conversationListView.init(new ArrayList<>());
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                try {
                    JSONObject jsonObject = new JSONObject(conversation.getExtField());
                    String avatar = jsonObject.optString("avatar");
                    String nickname = jsonObject.optString("nickname");
                    ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                            .withString("userId", conversation.conversationId())
                            .withString("nickname", nickname)
                            .withString("avatar", avatar)
                            .navigation();
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("删除错误数据");
                    EMClient.getInstance().chatManager().deleteConversation(conversation.conversationId(), false);
                    refresh();
                }
            }
        });
        EMClient.getInstance().chatManager().addMessageListener(this);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (mConversationList != null && mConversationList.size() > page * PAGE_SIZE) {
                    if (mConversationList.size() > (page + 1) * PAGE_SIZE) {
                        conversationListView.addData(mConversationList.subList(page * PAGE_SIZE, (page + 1) * PAGE_SIZE));
                    } else {
                        conversationListView.addData(mConversationList.subList(page * PAGE_SIZE, mConversationList.size()));
                        mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                mSmartRefreshLayout.finishLoadMore();
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
        return R.layout.news_fragement_news;
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
        mNestedScrollView.fling(0);
        mNestedScrollView.smoothScrollTo(0, 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteConversation(ConversationDelEvent event) {
        EMConversation tobeDeleteCons = conversationListView.getItem(event.position);
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

    public static NewsFragment newInstance(String title) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        newsFragment.setArguments(bundle);
        return newsFragment;
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
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
        MvpPre.userNews();
        if (NotificationUtils.areNotificationsEnabled()) {
            mClNotification.setVisibility(View.GONE);
        } else {
            mClNotification.setVisibility(View.VISIBLE);
        }
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
                    int orderCount = PreferencesUtils.getInt(MyApplication.getInstance(), Constants.ORDER_NEWS_COUNT, 0);
                    orderCount++;
                    PreferencesUtils.putInt(MyApplication.getInstance(), Constants.ORDER_NEWS_COUNT, orderCount);
                    PreferencesUtils.putString(MyApplication.getInstance(), Constants.ORDER_LAST_MSG, emMessage.getStringAttribute(Constant.EXTRA_MSG_STATUS_MSG, ""));
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
    public void userNewsSuccess(NewsModel newsModel) {
        if (newsModel.getInfo() == null) {
            mNivSys.setMsg("暂无消息");
        } else {
            mNivSys.setMsg(newsModel.getInfo().getContent());
        }
        mNivSys.setCount(newsModel.getCount());
    }

    @Override
    public void conversationComplete(List<EMConversation> list) {
        page = 0;
        mConversationList = new ArrayList<>(list);
        if (conversationListView != null) {
            if (list.size() < PAGE_SIZE) {
                conversationListView.refresh(list);
                if (mSmartRefreshLayout != null) {
                    mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            } else {
                conversationListView.refresh(list.subList(0, PAGE_SIZE));
            }
        }
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
        onDeleteConversation(new ConversationDelEvent(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position, deleteMessage));
        return true;
    }

    @OnClick({R.id.niv_sys, R.id.iv_friend_list, R.id.btn_open_notification, R.id.iv_close_notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.niv_sys:
//                ActivityUtils.startActivity(SystemNewsActivity.class);
                ARouter.getInstance().build(ARouteConstants.SYSTEM_NEWS).navigation();
                break;
            case R.id.iv_friend_list:
                ARouter.getInstance().build(ARouteConstants.ME_MY_FRIENDS).navigation();
                break;
            case R.id.btn_open_notification:
                NotificationPageHelper.open(getActivity());
                break;
            case R.id.iv_close_notification:
                mClNotification.setVisibility(View.GONE);
                break;
        }
    }
}
