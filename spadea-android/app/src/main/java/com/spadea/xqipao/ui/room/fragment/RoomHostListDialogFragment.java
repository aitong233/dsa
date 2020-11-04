package com.spadea.xqipao.ui.room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.flyco.tablayout.SlidingTabLayout;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.yuyin.widget.ScrollViewPager;
import com.spadea.xqipao.ui.room.presenter.RoomHostListPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/2 2:30 PM
 * 描述 describe
 */
public class RoomHostListDialogFragment extends BaseDialogFragment {
    @BindView(R.id.view_pager)
    ScrollViewPager mViewPager;

    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout mSlidingTabLayout;

    private String roomId;

    public static RoomHostListDialogFragment newInstance(String roomId) {
        RoomHostListDialogFragment fragment = new RoomHostListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initData() {
        mViewPager.setScroll(true);
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), roomId));
        mViewPager.setOffscreenPageLimit(3);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCurrentTab(0);
    }

    @Override
    protected void initView(View rootView) {
        if (getArguments() != null) {
            roomId = getArguments().getString("roomId");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_dialog_room_host_list;
    }

    @Override
    protected RoomHostListPresenter bindPresenter() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_r5_gradient_main));
        window.setGravity(Gravity.CENTER);
        int width = (int) (ScreenUtils.getScreenWidth() / 375.0 * 305);
        int height = (int) (width / 305.0 * 505);
        window.setLayout(width, height);
        window.setWindowAnimations(R.style.ShowDialogBottom);
        setCancelable(true);
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        private String roomId;

        MyPagerAdapter(FragmentManager fm, String roomId) {
            super(fm);
            this.roomId = roomId;
        }

        String[] titles = {"日榜", "周榜", "月榜"};

        @Override
        public Fragment getItem(int i) {
            return RoomHostListFragment.newInstance(i + 1, roomId);
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
