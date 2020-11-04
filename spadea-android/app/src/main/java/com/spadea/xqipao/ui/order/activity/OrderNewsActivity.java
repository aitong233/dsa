package com.spadea.xqipao.ui.order.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.OrderMsgResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.ui.order.contacts.OrderNewsContacts;
import com.spadea.xqipao.ui.order.presenter.OrderNewsPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.order.adapter.OrderNewsAdapter;
import com.spadea.xqipao.ui.order.status.OrderStatusEnum;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ORDER_NEWS, name = "订单消息")
public class OrderNewsActivity extends BaseActivity<OrderNewsPresenter> implements OrderNewsContacts.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private int page = 1;
    private OrderNewsAdapter mAdapter;

    public OrderNewsActivity() {
        super(R.layout.activity_order_news);
    }

    @Override
    protected void initData() {
        PreferencesUtils.putInt(MyApplication.getInstance(), Constants.ORDER_NEWS_COUNT, 0);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("订单消息");
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setEnableLoadMore(true);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.getList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.getList(page);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrderNewsAdapter();
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected OrderNewsPresenter bindPresenter() {
        return new OrderNewsPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    public void endLoading() {
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }

    @Override
    public void newsList(int page, OrderMsgResp resp) {
        if (page == 1) {
            mAdapter.setNewData(resp.getRecords());
        } else {
            mAdapter.addData(resp.getRecords());
        }
        if (page == resp.getPages()) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void refresh() {
        page = 1;
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    public void serviceUserSuccess(String uin) {
        try {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + uin + "&version=1";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } catch (Exception e) {
            ToastUtils.showShort("请先安装QQ");
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        OrderMsgResp.RecordsBean recordsBean = mAdapter.getItem(position);
        if (recordsBean == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_accept:
                if (recordsBean.getOrderStatus() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                    MvpPre.accompanyAccept(new UpdateOrderModel(recordsBean.getId(), 1));
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.BE_SERVE.getValue()) {
                    MvpPre.accompanyService(recordsBean.getId());
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.REFUNDING.getValue()) {
                    MvpPre.agreeRefund(recordsBean.getId());
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                    MvpPre.bossAcceptService(new UpdateOrderModel(recordsBean.getId(), 1));
                } else if (OrderStatusEnum.canFinishOrder(recordsBean.getOrderStatus())) {
                    MvpPre.bossConfirmOrder(recordsBean.getId());
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                    MvpPre.bossAgreeRefuseRefund(recordsBean.getId());
                } else if (OrderStatusEnum.canEvaluateOrder(recordsBean.getOrderStatus())) {
                    ARouter.getInstance().build(ARouters.ORDER_SCORE).withInt("orderId", recordsBean.getId()).withInt("type", recordsBean.getType()).navigation();
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.APPEALING.getValue()) {
                    MvpPre.serviceUser();
                }
                break;
            case R.id.tv_reject:
                if (recordsBean.getOrderStatus() == OrderStatusEnum.BE_CONFIRM.getValue()) {
                    MvpPre.accompanyAccept(new UpdateOrderModel(recordsBean.getId(), 0));
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.REFUNDING.getValue()) {
                    MvpPre.disAgreeRefund(recordsBean.getId());
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.FIRST_SERVE.getValue()) {
                    MvpPre.bossAcceptService(new UpdateOrderModel(recordsBean.getId(), 0));
                } else if (OrderStatusEnum.canFinishOrder(recordsBean.getOrderStatus())) {
                    MvpPre.bossRefundOrder(recordsBean.getId());
                } else if (recordsBean.getOrderStatus() == OrderStatusEnum.REFUND_REJECTED.getValue()) {
                    MvpPre.bossAppealing(recordsBean.getId(), String.valueOf(recordsBean.getUserId()), String.valueOf(recordsBean.getPlayUserId()));
                }
                break;
            case R.id.view_info:
                ARouter.getInstance().build(ARouters.ORDER_DETAIL).withInt("orderId", recordsBean.getId()).withInt("type", recordsBean.getType()).navigation();
                break;
        }
    }
}
