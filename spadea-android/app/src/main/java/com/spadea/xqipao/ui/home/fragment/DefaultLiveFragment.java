package com.spadea.xqipao.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.MultiRoomModel;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.home.presenter.DefaultLivePresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.adapter.DefaultLiveAdapter;
import com.spadea.xqipao.ui.home.contacts.DefaultLiveContacts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 默认列表模块
 */
public class DefaultLiveFragment extends BaseFragment<DefaultLivePresenter> implements DefaultLiveContacts.View {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private String type = "0";
    private DefaultLiveAdapter mDefaultLiveAdapter;


    public static DefaultLiveFragment newInstance(String title, String type) {
        DefaultLiveFragment emotionFragment = new DefaultLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("type", type);
        emotionFragment.setArguments(bundle);
        return emotionFragment;
    }


    @Override
    protected DefaultLivePresenter bindPresenter() {
        return new DefaultLivePresenter(this, mContext);
    }

    @Override
    protected void initData() {
        type = getArguments().getString("type", "0");
        MvpPre.getRoomList(type);
    }

    @Override
    protected void initView(View rootView) {
        recyclerview.setAdapter(mDefaultLiveAdapter = new DefaultLiveAdapter());
        recyclerview.setFocusable(false);
        GridLayoutManager layout = new GridLayoutManager(mContext, 3);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i < 3) {
                    return 1;
                }
                return 3;
            }
        });
        recyclerview.setLayoutManager(layout);
    }

    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.getRoomList(type);
            }
        });
        mDefaultLiveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiRoomModel item = mDefaultLiveAdapter.getItem(position);
                if (item != null) {
                    LiveRoomActivity.start(getActivity(), item.mRoomModel.getRoom_id());
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_emotion;
    }

    @Override
    public void showLoadings() {


    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void roomListtSuccess(List<RoomModel> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        List<MultiRoomModel> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            RoomModel roomModel = data.get(i);
            if (i < 3) {
                list.add(new MultiRoomModel(1, roomModel));
            } else {
                list.add(new MultiRoomModel(0, roomModel));
            }
        }
        mDefaultLiveAdapter.setNewData(list);
    }


    @Override
    public void roomListComplete() {
        smartRefreshLayout.finishRefresh();
    }


}
