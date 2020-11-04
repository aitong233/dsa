package com.qpyy.module.index.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CustomSlidingTabLayout;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.event.RankingTabSwitchEvent;
import com.qpyy.module.index.fragment.RankingFragment;
import com.qpyy.module.index.fragment.WeekStarFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.activity
 * 创建人 王欧
 * 创建时间 2020/6/28 1:01 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.INDEX_RANKING_LIST)
public class RankingListActivity extends BaseAppCompatActivity {
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTabLayout mSlidingTabLayout;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;

    @Autowired
    public String roomId;

    @Override
    protected void initData() {
        roomId = getIntent().getStringExtra("roomId");
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), roomId));
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCurrentTab(0);
    }

    @Override
    public boolean isLightMode() {
        return false;
    }

    @Override
    protected void initView() {
        mIvBack.setColorFilter(Color.WHITE);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                EventBus.getDefault().post(new RankingTabSwitchEvent(i));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_activity_ranking_list;
    }

    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles = new String[]{"魅力榜", "财富榜"};

        private String roomId;

        public MyFragmentPagerAdapter(FragmentManager fm, String roomId) {
            super(fm);
            this.roomId = roomId;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return RankingFragment.newInstance(roomId, RankingFragment.TYPE_CHARM);
            } else if (position == 1) {
                return RankingFragment.newInstance(roomId, RankingFragment.TYPE_WEALTH);
            }
            return WeekStarFragment.newInstance(roomId);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
