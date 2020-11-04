package com.qpyy.room.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.NewsListBean;
import com.qpyy.room.contacts.SystemNewsContacts;
import com.qpyy.room.presenter.SystemNewsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/7 14:51
 * 描述 describe
 */
public class RoomSysMessageDialogFragment extends BaseMvpDialogFragment<SystemNewsPresenter> implements SystemNewsContacts.View {

    private static final String TAG = "BaseDialogFragment";
    @BindView(R2.id.iv_dialog_sys_message_back)
    ImageView ivDialogSysMessageBack;
    @BindView(R2.id.tv_sys_message_window_title)
    TextView tvSysMessageWindowTitle;
    @BindView(R2.id.tv_read_all_sys_message)
    TextView tvReadAllSysMessage;
    @BindView(R2.id.rv_message_list)
    RecyclerView mRecycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private int page = 1;
    private BaseQuickAdapter<NewsListBean, BaseViewHolder> mAdapter;

    public static RoomSysMessageDialogFragment newInstance() {
        Bundle args = new Bundle();
        RoomSysMessageDialogFragment fragment = new RoomSysMessageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        mAdapter = new BaseQuickAdapter<NewsListBean, BaseViewHolder>(R.layout.room_rv_item_system_news) {
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
        Log.d(TAG, "(Start)启动了===========================RoomSysMessageDialogFragment");
        return R.layout.room_dialog_sys_message_list;
    }

    @Override
    protected SystemNewsPresenter bindPresenter() {
        return new SystemNewsPresenter(this, getActivity());
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


    @OnClick(R2.id.iv_dialog_sys_message_back)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showLoadings(String content) {

    }

    public static void show(FragmentManager manager) {
        new RoomSysMessageDialogFragment().show(manager, TAG);
    }
}
