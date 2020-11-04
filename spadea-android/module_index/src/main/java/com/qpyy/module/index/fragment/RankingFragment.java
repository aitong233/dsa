package com.qpyy.module.index.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lihang.ShadowLayout;
import com.qpyy.libcommon.base.BaseFragment;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.event.RankingTabSwitchEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.fragment
 * 创建人 王欧
 * 创建时间 2020/6/28 4:15 PM
 * 描述 describe
 */
public class RankingFragment extends BaseFragment {
    @BindView(R2.id.sl_day)
    ShadowLayout mSlDay;
    @BindView(R2.id.sl_week)
    ShadowLayout mSlWeek;
//    @BindView(R2.id.sl_month)
//    ShadowLayout mSlMonth;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.iv_bg)
    ImageView mIvBg;
    @BindView(R2.id.tv_day_1)
    TextView mTvDay1;
    @BindView(R2.id.tv_week_1)
    TextView mTvWeek1;
//    @BindView(R2.id.tv_month_1)
//    TextView mTvMonth1;
    private String roomId;
    private int type;

    public static final int TYPE_CHARM = 0;
    public static final int TYPE_WEALTH = 1;
    public static final int TYPE_WEEK_STAR = 2;

    public static RankingFragment newInstance(String roomId, int type) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putInt("type", type);
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchTab(RankingTabSwitchEvent event) {
        if (type != event.type) {
            mViewPager.setCurrentItem(2);
        }
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        type = arguments.getInt("type", TYPE_CHARM);
    }

    @Override
    protected void initData() {
        if (type == TYPE_CHARM) {
            mIvBg.setImageResource(R.mipmap.index_bg_charm_list);
            mTvDay1.setTextColor(Color.parseColor("#FF6BA4"));
            mTvWeek1.setTextColor(Color.parseColor("#FF6BA4"));
//            mTvMonth1.setTextColor(Color.parseColor("#FF6BA4"));
        } else if (type == TYPE_WEALTH) {
            mIvBg.setImageResource(R.mipmap.index_bg_wealth_list);
            mTvDay1.setTextColor(Color.parseColor("#E59E05"));
            mTvWeek1.setTextColor(Color.parseColor("#E59E05"));
//            mTvMonth1.setTextColor(Color.parseColor("#E59E05"));
        } else if (type == TYPE_WEEK_STAR) {
            mIvBg.setImageResource(R.mipmap.index_bg_week_star_list);
            mTvDay1.setTextColor(Color.parseColor("#FF9797"));
            mTvWeek1.setTextColor(Color.parseColor("#FF9797"));
//            mTvMonth1.setTextColor(Color.parseColor("#FF9797"));
        }
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), roomId, type));
    }

    @Override
    protected void initView() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                resetTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_ranking;
    }

    @OnClick({R2.id.tv_day, R2.id.tv_week})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_day) {
            resetTab(0);
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.tv_week) {
            resetTab(1);
            mViewPager.setCurrentItem(1);
        }
    }

    private void resetTab(int index) {
        mSlDay.setVisibility(View.GONE);
        mSlWeek.setVisibility(View.GONE);
//        mSlMonth.setVisibility(View.GONE);
        if (index == 0) {
            mSlDay.setVisibility(View.VISIBLE);
        } else if (index == 1) {
            mSlWeek.setVisibility(View.VISIBLE);
        } else if (index == 2) {
//            mSlMonth.setVisibility(View.VISIBLE);
        }
    }

    private static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


        private String roomId;

        private int type;

        public MyFragmentPagerAdapter(FragmentManager fm, String roomId, int type) {
            super(fm);
            this.roomId = roomId;
            this.type = type;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return RankingListFragment.newInstance(roomId, type, RankingListFragment.TYPE_DAY);
            } else if (position == 1) {
                return RankingListFragment.newInstance(roomId, type, RankingListFragment.TYPE_WEEK);
            }
            return RankingListFragment.newInstance(roomId, type, RankingListFragment.TYPE_MONTH);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
