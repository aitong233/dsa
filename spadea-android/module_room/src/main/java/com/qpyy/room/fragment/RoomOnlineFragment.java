package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.event.UserDetailShowEvent;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.OnlineListAdpter;
import com.qpyy.room.bean.OnlineListResp;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomOnlineResp;
import com.qpyy.room.contacts.RoomOnlineContacts;
import com.qpyy.room.event.BaoWheatEvent;
import com.qpyy.room.presenter.RoomOnlinePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.activity
 * 创建人 黄强
 * 创建时间 2020/7/25 11:19
 * 描述 describe
 */
public class RoomOnlineFragment extends BaseMvpFragment<RoomOnlinePresenter> implements RoomOnlineContacts.View {


    @BindView(R2.id.online_people_num)
    TextView onlinePeopleNum;
    @BindView(R2.id.room_online_list)
    RecyclerView roomOnlineList;
    @BindView(R2.id.srl_online_refresh)
    SmartRefreshLayout srlOnlineRefresh;

    private OnlineListAdpter OLApter;//房间在线item适配器
    private static String roomId;//房间ID
    private static int page = 1;// 页
    private RoomInfoResp roomInfoResp;

    /**
     * newInstance 传递数据给fragment
     *
     * @param resp
     * @return
     */
    public static RoomOnlineFragment newInstance(RoomInfoResp resp) {
        RoomOnlineFragment roomOnlineFragment = new RoomOnlineFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("resp", resp);
        roomOnlineFragment.setArguments(bundle);
        return roomOnlineFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wheatEvent(BaoWheatEvent event) {
        OLApter.setManager(event.manager);
        if (event.manager) {
            srlOnlineRefresh.autoRefresh();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated && isVisibleToUser ) {
            page = 1;//重新获取第一页数据
            MvpPre.getOnlineList(roomId, page);

        }
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomInfoResp = (RoomInfoResp) getArguments().getSerializable("resp");
        roomId = roomInfoResp.getRoom_info().getRoom_id();
    }

    @Override
    protected void initData() {
        srlOnlineRefresh.setEnableLoadMore(true);//开启下拉加载更多
        srlOnlineRefresh.setEnableRefresh(true);//开启下拉刷新
        srlOnlineRefresh.autoRefresh();//自动刷新

    }

    @Override
    protected void initView() {
        //初始化试图
        OLApter = new OnlineListAdpter();
        roomOnlineList.setLayoutManager(new LinearLayoutManager(getActivity()));
        roomOnlineList.setAdapter(OLApter);
//        OLApter.setEmptyView(R.layout.room_rv_item_online);//设置数据为空时布局
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_online_page;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.e("显示状态", hidden);
    }

    @Override
    protected void initListener() {
        super.initListener();
        srlOnlineRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            //上拉加载更多
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                MvpPre.getOnlineList(roomId, ++page);//加载更多
            }

            //下拉刷新
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlOnlineRefresh.setEnableRefresh(true);//开启下拉刷新
                page = 1;//重新获取第一页数据
                MvpPre.getOnlineList(roomId, page);
            }
        });

        OLApter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OnlineListResp item = OLApter.getItem(position);
                //抱麦
                if (view.getId() == R.id.iv_bao_wheat) {
                    Logger.d("你点击了ID为 " + item.getUser_id() + " 的爆麦");
                    MvpPre.putOnWheat(roomId, item.getUser_id());
                }
                //头像
                if (view.getId() == R.id.room_item_head) {
                    Logger.d("你点击了ID为" + item.getUser_id() + "头像");
                    EventBus.getDefault().post(new UserDetailShowEvent(roomId, item.getUser_id()));
                }
            }
        });
    }


    @Override
    public void setOnlineListView(RoomOnlineResp resp, int page) {
        if (page == 1) {
            onlinePeopleNum.setText("在线列表(" + resp.getTotal() + "人)");
            OLApter.setNewData(resp.getList());
        } else {
            OLApter.addData(resp.getList());
        }
        if (resp.getList() == null || resp.getList().size() == 0) {
            srlOnlineRefresh.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void roomOnlineComplete() {
        srlOnlineRefresh.finishRefresh();//完成刷新
        srlOnlineRefresh.finishLoadMore();//完成加载更多
    }


    @Override
    protected RoomOnlinePresenter bindPresenter() {
        return new RoomOnlinePresenter(this, getActivity());
    }
}
