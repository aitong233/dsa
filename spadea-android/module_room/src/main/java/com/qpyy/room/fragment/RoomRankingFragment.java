package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.event.RefreshRoomRankEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.activity
 * 创建人 黄强
 * 创建时间 2020/7/24 17:43
 * 描述 describe
 */
public class RoomRankingFragment extends BaseMvpFragment {

    @BindView(R2.id.charm_type)
    LinearLayout charmType;
    @BindView(R2.id.wealth_type)
    LinearLayout wealthType;
    @BindView(R2.id.vp_parent)
    ViewPager vpParent;
    private String mRoomId;
    public static final int TYPE_CHARM = 1;//魅力榜
    public static final int TYPE_WEALTH = 2;//财富榜
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    /**
     * newInstance 初始化fragment
     *
     * @return
     */
    public static RoomRankingFragment newInstance(String roomId) {
        RoomRankingFragment roomRankingFragment = new RoomRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        roomRankingFragment.setArguments(bundle);
        return roomRankingFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRoomId != null && isVisibleToUser) {
            int rankType = vpParent.getCurrentItem() + 1;
            EventBus.getDefault().post(new RefreshRoomRankEvent(rankType));
        }
    }

    @Override
    protected void initData() {
        mRoomId = getArguments().getString("roomId");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RankingParentFragment.newInstance(mRoomId, TYPE_CHARM));
        fragments.add(RankingParentFragment.newInstance(mRoomId, TYPE_WEALTH));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragments, getChildFragmentManager());
        vpParent.setAdapter(myFragmentPagerAdapter);
    }

    @Override
    protected void initView() {
        vpParent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    charmType.setBackground(getResources().getDrawable(R.mipmap.room_rank_menu_current));
                    wealthType.setBackground(getResources().getDrawable(R.color.transparent));
                } else {
                    wealthType.setBackground(getResources().getDrawable(R.mipmap.room_rank_menu_current));
                    charmType.setBackground(getResources().getDrawable(R.color.transparent));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_fill_ranking_page;
    }

    @Override
    protected void initListener() {
        super.initListener();
        vpParent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {//背景更换
                    charmType.setBackground(getResources().getDrawable(R.mipmap.room_rank_menu_current));
                    wealthType.setBackground(getResources().getDrawable(R.color.transparent));
                } else if (i == 1) {//背景更换
                    wealthType.setBackground(getResources().getDrawable(R.mipmap.room_rank_menu_current));
                    charmType.setBackground(getResources().getDrawable(R.color.transparent));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @OnClick({R2.id.charm_type, R2.id.wealth_type})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.charm_type) {
            vpParent.setCurrentItem(0);
        } else {
            vpParent.setCurrentItem(1);
        }

    }


    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

}
