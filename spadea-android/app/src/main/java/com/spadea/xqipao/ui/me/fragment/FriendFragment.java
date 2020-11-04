package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.FriendContacts;
import com.spadea.xqipao.ui.me.presenter.FriendPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.me.adapter.FriendAdapter;

import java.util.List;

import butterknife.BindView;

public class FriendFragment extends BaseFragment<FriendPresenter> implements FriendContacts.View {


    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private FriendAdapter friendAdapter;

    private int type = 0;

    private int page = 1;


    public static Fragment newInstance(int type) {
        FriendFragment friendFragment = new FriendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        friendFragment.setArguments(bundle);
        return friendFragment;
    }


    @Override
    protected FriendPresenter bindPresenter() {
        return new FriendPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        type = getArguments().getInt("type", 0);
        switch (type) {
            case 0:
                MvpPre.friendList(page);
                break;
            case 1:
                MvpPre.followList(page);
                break;
            case 2:
                MvpPre.fansList(page);
                break;
        }
    }

    @Override
    protected void initView(View rootView) {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(friendAdapter = new FriendAdapter());
        friendAdapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected void initListener() {
        super.initListener();
        friendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FriendModel item = friendAdapter.getItem(position);
                switch (type) {
                    case 0:
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getFriend_id()).navigation();
                        break;
                    case 1:
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getFollowed_user()).navigation();
                        break;
                    case 2:
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
                        break;
                }
            }
        });
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadDatas();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                loadDatas();
            }
        });
    }

    private void loadDatas() {
        switch (type) {
            case 0:
                MvpPre.friendList(page);
                break;
            case 1:
                MvpPre.followList(page);
                break;
            case 2:
                MvpPre.fansList(page);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void setData(int page, List<FriendModel> data) {
        if (page == 1) {
            friendAdapter.setNewData(data);
        } else {
            friendAdapter.addData(data);
        }
        if (data == null || data.size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void onComplete() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
