package com.qpyy.module_news.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module_news.R;
import com.qpyy.module_news.R2;
import com.qpyy.module_news.bean.NewsListBean;
import com.qpyy.module_news.contacts.SystemNewsContacts;
import com.qpyy.module_news.presenter.SystemNewsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.SYSTEM_NEWS)
public class SystemNewsActivity extends BaseMvpActivity<SystemNewsPresenter> implements SystemNewsContacts.View {


    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private int page = 1;
    private BaseQuickAdapter<NewsListBean, BaseViewHolder> mAdapter;

    @Override
    protected void initData() {
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle.setText("系统通知");
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
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<NewsListBean, BaseViewHolder>(R.layout.news_rv_item_system_news) {
            @Override
            protected void convert(BaseViewHolder helper, NewsListBean item) {
                helper.setText(R.id.tv_time, item.getAdd_time());
                helper.setText(R.id.tv_content, Html.fromHtml(item.getContent()));
                helper.getView(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getAction_type() != null) {
                            switch (item.getAction_type()) {
                                case "1"://跳转房间
                                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getAction_id()).navigation();
                                    break;
                                case "3"://QQ客服
                                    MvpPre.serviceUser();
                                    break;
                                case "2"://我的背包
                                    ARouter.getInstance().build(ARouteConstants.ME_KNAPSACK).navigation();
                                    break;
                            }
                        }
                    }
                });
            }
        };
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.news_activity_system_news;
    }

    @Override
    protected SystemNewsPresenter bindPresenter() {
        return new SystemNewsPresenter(this, this);
    }

    @Override
    public void newsList(List<NewsListBean> listBeans) {
        if (page == 1) {
            mAdapter.setNewData(listBeans);
        } else {
            mAdapter.addData(listBeans);
        }
        if (listBeans == null || listBeans.size() == 0) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void loadComplete() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void serviceSuccess(String data) {
        try {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + data + "&version=1";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } catch (Exception e) {
            ToastUtils.show("请先安装QQ");
        }
    }

    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
