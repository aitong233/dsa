package com.spadea.xqipao.ui.me.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.CashTypeModel;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.PaymentDetailsContacter;
import com.spadea.xqipao.ui.me.presenter.PaymentDetailsPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.ConditionAdapter;
import com.spadea.xqipao.ui.me.adapter.DetailedAdapter;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 资金明细
 */

public class PaymentDetailsActivity extends BaseActivity<PaymentDetailsPresenter> implements PaymentDetailsContacter.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView_type)
    RecyclerView recyclerViewType;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.rl_screen)
    RelativeLayout rlScreen;


    private boolean b = false;
    private DetailedAdapter detailedAdapter;
    private ConditionAdapter conditionAdapter;


    public PaymentDetailsActivity() {
        super(R.layout.activity_payment_details);
    }

    @Override
    protected void initData() {
        MvpPre.cashType();
        MvpPre.getCashLog(-1, true);
    }

    @Override
    protected void initView() {
        tvTitle.setText("交易记录");
        tvRight.setText("筛选");
        tvRight.setVisibility(View.VISIBLE);
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(detailedAdapter = new DetailedAdapter());
        detailedAdapter.setEmptyView(commonEmptyView);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.getCashLog(conditionAdapter.getType(), true);
            }
        });
        recyclerViewType.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewType.setAdapter(conditionAdapter = new ConditionAdapter());

        conditionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                conditionAdapter.setIndewx(position);
            }
        });
        detailedAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                MvpPre.getCashLog(conditionAdapter.getType(), false);
            }
        }, recyclerview);
        detailedAdapter.loadMoreEnd();
    }

    @Override
    protected PaymentDetailsPresenter bindPresenter() {
        return new PaymentDetailsPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.rl_screen, R.id.tv_reset, R.id.tv_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if (conditionAdapter.getData() == null || conditionAdapter.getData().size() == 0) {
                    return;
                }
                if (b) {
                    b = false;
                    rlScreen.setVisibility(View.GONE);
                } else {
                    b = true;
                    rlScreen.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_screen:
                b = false;
                rlScreen.setVisibility(View.GONE);
                break;
            case R.id.tv_reset:
                b = false;
                rlScreen.setVisibility(View.GONE);
                conditionAdapter.setIndewx(0);
                MvpPre.getCashLog(conditionAdapter.getType(), true);
                break;
            case R.id.tv_ok:
                b = false;
                rlScreen.setVisibility(View.GONE);
                MvpPre.getCashLog(conditionAdapter.getType(), true);
                break;
        }
    }


    @Override
    public void getCashLogSuccess(List<EarningsModel.EarningInfo> list, boolean x) {
        smartRefreshLayout.finishRefresh();
        if (list.size() == 0) {
            detailedAdapter.loadMoreEnd();
        } else {
            detailedAdapter.loadMoreComplete();
        }
        if (x) {
            detailedAdapter.setNewData(list);
        } else {
            detailedAdapter.addData(list);
        }
    }

    @Override
    public void cashTypeSuccess(List<CashTypeModel> data) {
        conditionAdapter.setNewData(data);
    }
}
