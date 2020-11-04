package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Gravity;
import android.view.Window;

import com.blankj.utilcode.util.ScreenUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.widget.ScrollViewPager;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.presenter.RoomHostListPresenter;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/2 2:30 PM
 * 描述 describe
 */
public class RoomHostListDialogFragment extends BaseMvpDialogFragment {
    @BindView(R2.id.view_pager)
    ScrollViewPager mViewPager;

    @BindView(R2.id.sliding_tab_layout)
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
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
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
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_dialog_room_host_list;
    }

    @Override
    protected RoomHostListPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.room_bg_r15_0e0e0e));
        window.setGravity(Gravity.CENTER);
        int width = (int) (ScreenUtils.getScreenWidth() / 375.0 * 305);
        int height = (int) (width / 305.0 * 525);
        window.setLayout(width, height);
        setCancelable(true);
    }

    @Override
    public void showLoadings(String content) {

    }

    private static class MyPagerAdapter extends FragmentStatePagerAdapter {

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
