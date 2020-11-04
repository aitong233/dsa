package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.MyOrderContacts;
import com.spadea.xqipao.ui.me.presenter.MyOrderPresenter;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;

import butterknife.BindView;

public class MyOrderFragment extends BaseFragment<MyOrderPresenter> implements MyOrderContacts.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private int type;

    private int page = 1;
    private MyOrderAdapter mAdapter;

    public static MyOrderFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected MyOrderPresenter bindPresenter() {
        return new MyOrderPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initView(View rootView) {
        type = getArguments().getInt("type", MyOrderAdapter.TYPE_RECV);
        mSmartRefreshLayout.setEnableLoadMore(true);
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.getOrder(type, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.getOrder(type, page);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyOrderAdapter(type);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_my_order;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        OrdersResp.RecordsBean item = mAdapter.getItem(position);
        if (item == null) {
            return;
        }
        item.setType(type);
        if ((type == MyOrderAdapter.TYPE_SEND && item.getEvaluationStatus() > 0) || type == MyOrderAdapter.TYPE_RECV && item.getPlayEvaluationStatus() > 0) {
            ARouter.getInstance().build(ARouters.ORDER_SCORE).withInt("orderId", item.getId()).withInt("type", type).navigation();
        } else {
            ARouter.getInstance().build(ARouters.ORDER_DETAIL).withInt("orderId", item.getId()).withInt("type", type).navigation();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        OrdersResp.RecordsBean item = mAdapter.getItem(position);
        if (item == null) {
            return;
        }
        item.setType(type);
        switch (view.getId()) {
            case R.id.btn_remark:
                ARouter.getInstance().build(ARouters.ORDER_SCORE).withInt("orderId", item.getId()).withInt("type", type).navigation();
                break;
            case R.id.btn_order:
                if (type == MyOrderAdapter.TYPE_RECV) {
                    ARouter.getInstance().build(ARouters.CONFIRM_ORDER).withString("userId", String.valueOf(item.getPlayUserId())).withInt("id", item.getApplyId()).navigation();
                } else {
                    ARouter.getInstance().build(ARouters.CONFIRM_ORDER).withString("userId", String.valueOf(item.getUserId())).withInt("id", item.getApplyId()).navigation();
                }
                break;
        }
    }

    @Override
    public void endLoading() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void ordersResp(int page, OrdersResp ordersResp) {
        if (page == 1) {
            mAdapter.setNewData(ordersResp.getRecords());
        } else {
            mAdapter.addData(ordersResp.getRecords());
        }
        if (page == ordersResp.getPages()) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }
}