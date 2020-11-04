package com.qpyy.module.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.adapter.RoomAdapter;
import com.qpyy.module.index.bean.MultiRoomModel;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.contacts.RoomListContacts;
import com.qpyy.module.index.event.BannerRefreshEvent;
import com.qpyy.module.index.presenter.RoomListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class RoomListFragment extends BaseMvpFragment<RoomListPresenter> implements RoomListContacts.View {

    @BindView(R2.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private String type;
    private RoomAdapter mAdapter;

    public static RoomListFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        RoomListFragment fragment = new RoomListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        type = arguments.getString("type");
    }

    @Override
    protected RoomListPresenter bindPresenter() {
        return new RoomListPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.getRoomList(type);
    }

    @Override
    protected void initView() {
        mAdapter = new RoomAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setFocusable(false);
        GridLayoutManager layout = new GridLayoutManager(getActivity(), 3);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i < 3) {
                    return 1;
                }
                return 3;
            }
        });
        mRecycleView.setLayoutManager(layout);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new BannerRefreshEvent());
                MvpPre.getRoomList(type);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiRoomModel item = mAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.mRoomModel.getRoom_id()).navigation();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragement_room_list;
    }

    @Override
    public void roomList(List<RoomModel> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        List<MultiRoomModel> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            RoomModel roomModel = data.get(i);
            if (i < 3) {
                list.add(new MultiRoomModel(RoomAdapter.TYPE_TOP, roomModel));
            } else {
                list.add(new MultiRoomModel(RoomAdapter.TYPE_NORMAL, roomModel));
            }
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void finishRefreshLoadMore() {
        mSmartRefreshLayout.finishRefresh();
    }
}