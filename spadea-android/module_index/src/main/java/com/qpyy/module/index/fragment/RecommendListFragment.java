package com.qpyy.module.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.adapter.RecommendAdapter;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.contacts.RecommendListContacts;
import com.qpyy.module.index.event.BannerRefreshEvent;
import com.qpyy.module.index.presenter.RecommendListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;


public class RecommendListFragment extends BaseMvpFragment<RecommendListPresenter> implements RecommendListContacts.View {
    @BindView(R2.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private String type;
    private RecommendAdapter mAdapter;
    private int page = 1;

    public static RecommendListFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        RecommendListFragment fragment = new RecommendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        type = arguments.getString("type");
    }

    @Override
    protected RecommendListPresenter bindPresenter() {
        return new RecommendListPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.getRoomList(page);
    }

    @Override
    protected void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.getRoomList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new BannerRefreshEvent());
                page = 1;
                MvpPre.getRoomList(page);
            }
        });
        mAdapter = new RecommendAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomModel item = mAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragement_recommend_list;
    }


    @Override
    public void roomList(List<RoomModel> data, int page) {
        if (ObjectUtils.isEmpty(data)) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
            mAdapter.loadMoreEnd();
        } else {
            if (page == 1) {
                mAdapter.setNewData(data);
            } else {
                mAdapter.addData(data);
            }
        }

    }

    @Override
    public void finishRefreshLoadMore() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }
}