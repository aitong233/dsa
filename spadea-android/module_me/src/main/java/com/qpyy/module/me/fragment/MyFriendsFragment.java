package com.qpyy.module.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.adapter.FriendsAdapter;
import com.qpyy.module.me.bean.FriendBean;
import com.qpyy.module.me.contacts.MyFriendsConacts;
import com.qpyy.module.me.presenter.MyFriendsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的好友
 */
public class MyFriendsFragment extends BaseMvpFragment<MyFriendsPresenter> implements MyFriendsConacts.View {


    @BindView(R2.id.rl_serach)
    RelativeLayout rlSerach;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.tv_search_hint)
    TextView tvSearchHint;

    private FriendsAdapter mFriendsAdapter;

    private int page = 1;

    private int type;


    public static Fragment newInstance(int type) {
        MyFriendsFragment fragment = new MyFriendsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        type = arguments.getInt("type", 0);
    }

    @Override
    protected MyFriendsPresenter bindPresenter() {
        return new MyFriendsPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        smartRefreshLayout.autoRefresh();
        if (type == 0) {
            tvSearchHint.setText("请输入好友昵称");
        } else if (type == 1) {
            tvSearchHint.setText("请输入关注昵称");
        } else {
            tvSearchHint.setText("请输入粉丝昵称");
        }
    }

    @Override
    protected void initView() {
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.getData(type, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.getData(type, page);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mFriendsAdapter = new FriendsAdapter());
        mFriendsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FriendBean item = mFriendsAdapter.getItem(position);
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_my_friends;
    }

    @Override
    public void setData(int page, List<FriendBean> data) {
        if (page == 1) {
            mFriendsAdapter.setNewData(data);
        } else {
            mFriendsAdapter.addData(data);
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

    @OnClick(R2.id.rl_serach)
    public void onSearchClick(View view) {
        ARouter.getInstance().build(ARouteConstants.SEARCH_FRIEND).withInt("type", type).navigation();
    }


}
