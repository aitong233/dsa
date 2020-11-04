package com.qpyy.module_news.fragment;

import android.support.constraint.ConstraintLayout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NotificationUtils;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module_news.R;
import com.qpyy.module_news.R2;
import com.qpyy.module_news.contacts.NewsContacts;
import com.qpyy.module_news.presenter.NewsPresenter;
import com.qpyy.module_news.utils.NotificationPageHelper;
import com.qpyy.module_news.widget.NewsItemView;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsFragment extends BaseMvpFragment<NewsPresenter> implements NewsContacts.View, EMMessageListener {

    @BindView(R2.id.niv_sys)
    NewsItemView mNivSys;
    @BindView(R2.id.list)
    EaseConversationList mEaseConversationList;
    @BindView(R2.id.cl_notification)
    ConstraintLayout mClNotification;


    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected NewsPresenter bindPresenter() {
        return new NewsPresenter(this, getActivity());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        registerForContextMenu(mEaseConversationList);
        mEaseConversationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = mEaseConversationList.getItem(position);
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
                    ToastUtils.show("删除错误数据");
                    EMClient.getInstance().chatManager().deleteConversation(conversation.conversationId(), false);
                    MvpPre.refreshConversation();
                }
            }
        });
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NotificationUtils.areNotificationsEnabled()) {
            mClNotification.setVisibility(View.GONE);
        } else {
            mClNotification.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.news_fragement_news;
    }


    @OnClick({R2.id.iv_friend_list, R2.id.niv_sys, R2.id.btn_open_notification, R2.id.iv_close_notification})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_friend_list) {
            ARouter.getInstance().build(ARouteConstants.ME_MY_FRIENDS).navigation();
        } else if (id == R.id.niv_sys) {

        } else if (id == R.id.btn_open_notification) {
            NotificationPageHelper.open(getActivity());
        } else if (id == R.id.iv_close_notification) {
            mClNotification.setVisibility(View.GONE);
        }
    }


    @Override
    public void conversationComplete(List<EMConversation> list) {
        if (mEaseConversationList != null) {
            mEaseConversationList.refresh(list);
        }
    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.news_em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
        EMConversation tobeDeleteCons = mEaseConversationList.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        if (tobeDeleteCons == null) {
            return true;
        }
        if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
//            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
//            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
            // To delete the native stored adked users in this conversation.
            if (deleteMessage) {
                EaseDingMessageHelper.get().delete(tobeDeleteCons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MvpPre.refreshConversation();
        return true;
    }
}