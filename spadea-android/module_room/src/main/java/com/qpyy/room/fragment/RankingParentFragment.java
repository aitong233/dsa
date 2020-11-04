package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.event.RefreshRankListEvent;
import com.qpyy.room.event.RefreshRoomRankEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/8/14 18:09
 * 描述 describe
 */
public class RankingParentFragment extends BaseMvpFragment {

    //成员变量 + 全局变量
    public static final int TYPE_DATA = 1;//日榜
    public static final int TYPE_WEEK = 2;//周榜
    public static final int TYPE_MON = 3;//月榜
    private static String mRoomId;//房间ID
    private int rankType = 1;//统计类型（魅力 / 财富）
    private int dataType = 1;//统计周期（日/周/月）
    @BindView(R2.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.vp_rank_child)
    ViewPager vpRankChild;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public static RankingParentFragment newInstance(String roomId, int rankType) {
        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putInt("rankType", rankType);
        RankingParentFragment fragment = new RankingParentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated && isVisibleToUser) {
            EventBus.getDefault().post(new RefreshRankListEvent(rankType, vpRankChild.getCurrentItem() + 1));
        }
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mRoomId = getArguments().getString("roomId");
        rankType = getArguments().getInt("rankType");
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RankingChildFragment.newInstance(mRoomId, TYPE_DATA, rankType));
        fragments.add(RankingChildFragment.newInstance(mRoomId, TYPE_WEEK, rankType));
//        fragments.add(RankingChildFragment.newInstance(mRoomId, TYPE_MON, rankType));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragments, getChildFragmentManager());
        vpRankChild.setAdapter(myFragmentPagerAdapter);
        String[] title = new String[]{"日榜", "周榜"};
        slidingTabLayout.setViewPager(vpRankChild, title);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {
                LogUtils.e("slidingTabLayout", "onTabReselect", rankType);
                EventBus.getDefault().post(new RefreshRankListEvent(rankType, position + 1));
            }
        });
        vpRankChild.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                LogUtils.e("vpRankChild", "onPageSelected", rankType);
                EventBus.getDefault().post(new RefreshRankListEvent(rankType, i + 1));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    /**
     * 发送事件刷新魅力榜或财富榜当前显示的榜单
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RefreshRoomRankEvent refreshRoomRankEvent) {
        if (mRoomId != null && rankType == refreshRoomRankEvent.getRankType()) {
            EventBus.getDefault().post(new RefreshRankListEvent(rankType, vpRankChild.getCurrentItem() + 1));
        }
    }


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_ranking_parent;
    }

}
