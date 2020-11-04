package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.xqipao.data.OnlineModel;
import com.spadea.xqipao.ui.room.presenter.OnlinePresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.room.adapter.OnLineAdapter;
import com.spadea.xqipao.ui.room.contacts.OnlineContacts;

import java.util.List;

import butterknife.BindView;

public class OnlineFragment extends BaseFragment<OnlinePresenter> implements OnlineContacts.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private String mRoomId;
    private OnLineAdapter onLineAdapter;
    private RoomFragmentListener mRoomFragmentListener;

    private int page = 1;


    public static OnlineFragment newInstance(String roomId) {
        OnlineFragment onlineFragment = new OnlineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("RoomId", roomId);
        onlineFragment.setArguments(bundle);
        return onlineFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }

    @Override
    protected OnlinePresenter bindPresenter() {
        return new OnlinePresenter(this, mContext);
    }

    @Override
    protected void initData() {
        mRoomId = getArguments().getString("RoomId");
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(onLineAdapter = new OnLineAdapter());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.e("显示状态", hidden);
    }

    @Override
    protected void initListener() {
        onLineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OnlineModel item = onLineAdapter.getItem(position);
                if (mRoomFragmentListener != null) {
                    mRoomFragmentListener.getRoomUserInfo(item.getUser_id());
                }
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.roomOnline(mRoomId, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.setEnableLoadMore(true);
                page = 1;
                MvpPre.roomOnline(mRoomId, page);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void setRoomOnlineData(List<OnlineModel> data, int page) {
        if (data == null || data.size() == 0) {
            smartRefreshLayout.setEnableLoadMore(false);
            return;
        }
        if (page == 1) {
            onLineAdapter.setNewData(data);
        } else {
            onLineAdapter.addData(data);
        }
    }

    @Override
    public void roomOnlineComplete() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
